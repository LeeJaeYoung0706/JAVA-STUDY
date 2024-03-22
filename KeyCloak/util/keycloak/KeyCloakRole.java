package com.keti.iam.idthub.util.keycloak;

import com.keti.iam.idthub.util.exception.RestException;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.Collections;
import java.util.List;

@Component
public class KeyCloakRole {

    private final KeyCloakResourceAPI keyCloak;
    private final KeyCloakClient keyCloakClient;
    private final KeyCloakUser keyCloakUser;

    @Autowired
    public KeyCloakRole(KeyCloakResourceAPI keyCloak, KeyCloakClient keyCloakClient, KeyCloakUser keyCloakUser) {
        this.keyCloak = keyCloak;
        this.keyCloakClient = keyCloakClient;
        this.keyCloakUser = keyCloakUser;
    }

    /**
     * RoleResource   # 클라이언트에 존재하는 롤 정보 가져오기
     * @param roleName
     * @return
     */
    public RoleRepresentation clientRoleFind(String roleName) throws RestException {
        try {
            return keyCloak.getRealmResource().clients().get(keyCloakClient.clientFind().getId()).roles().get(roleName).toRepresentation();
        } catch (NotFoundException e) {
            throw new RestException("KeyCloak Error Client Role Not Found" , 404);
        }
    }

    /**
     * 롤 생성
     * @param roleName
     */
    public void createRole(String roleName) throws RestException {
        RoleRepresentation roleRepresentation = new RoleRepresentation();
        roleRepresentation.setClientRole(true);
        roleRepresentation.setName(roleName);
        keyCloak.getRealmResource().clients().get(keyCloakClient.clientFind().getId()).roles().create(roleRepresentation);
    }

    /**
     * 유저 클라이언트 단위 롤 검색
     * @param id
     * @return
     */
    public List<RoleRepresentation> userRoleSelect(String id) throws RestException{
        try {
            UserResource userResource = keyCloakUser.getUserResource(id);
            return userResource.roles().clientLevel(keyCloakClient.clientFind()
                    .getId()).listAll();
        } catch (NotFoundException e) {
            throw new RestException( "KeyCloak Error UserRole Not Found" , 404);
        }
    }

    /**
     * 유저가 가지고 있는 롤 맵핑 예를 들어 USER = USER true , USER = ADMIN false
     * @param id
     * @param roleName
     * @return
     * @throws NotFoundException
     */
    public boolean isUserRoleFindByRoleName(String id , String roleName) throws RestException {
        return keyCloakUser.getUserResource(id).roles().clientLevel(keyCloakClient.clientFind()
                .getId()).listAll().contains(clientRoleFind(roleName));
    }

    /**
     * 유저 ROLE 권한 주기
     * @param userId
     * @param userRole
     */
    public void userRoleSetting(String userId ,String userRole ) throws RestException{
        keyCloakUser.getUserResource(userId)
                .roles()
                .clientLevel(
                        keyCloakClient.clientFind()
                                .getId())
                .add(Collections.singletonList(
                        clientRoleFind(userRole))); // 유저 롤 셋팅
    }
}
