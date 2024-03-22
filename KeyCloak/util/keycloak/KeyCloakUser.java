package com.keti.iam.idthub.util.keycloak;


import com.keti.iam.idthub.util.exception.RestException;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KeyCloakUser {
    private final KeyCloakResourceAPI keyCloak;

    @Autowired
    public KeyCloakUser(KeyCloakResourceAPI keyCloak) {
        this.keyCloak = keyCloak;
    }

    /**
     *  keyCloak create User   # 키클락 유저 생성 전 셋팅
     * @param email
     * @return
     */
    protected UserRepresentation createKeyCloakUser(String email){
        UserRepresentation user = new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(email);
        user.setEmail(email);
        return user;
    }

    /**
     * 키클락 로그인 아이디로 정보찾기
     * @param LoginId
     * @return
     */
    public List<UserRepresentation> keyCloakUserFindByLoginId(String LoginId)  throws RestException {
        try {
            return keyCloak.getRealmResource().users()
                    .search(LoginId);
        } catch (NotFoundException e) {
            throw new RestException( "KeyCloak Error User Not Found" , 404);
        }
    }

    /**
     * 아이디로 유저 정보 가져오기
     * @param id
     * @return
     * @throws NotFoundException
     */
    public UserResource keyCloakUserInfoFindById(String id) throws RestException {
        try {
            return keyCloak.getUsersResource().get(id);
        } catch (NotFoundException e) {
            throw new RestException ("KeyCloak Error UserResource Not Found" , 404);
        }
    }


    /**
     * UserResource   # 해당하는 아이디의 유저 정보 가져오기
     * @param userId
     * @return
     */
    public UserResource getUserResource(String userId) throws RestException {
        try {
            return keyCloak.getUsersResource().get(userId);
        } catch (NotFoundException e) {
            throw new RestException("KeyCloak Error UserResource Not Found" , 404);
        }
    }

    /**
     * KeyCloak user create Response  #키클록 유저 생성시 리턴 값
     * @param email
     * @return
     */
    public Response createKeyCloakUserResponse(String email){
        UserRepresentation user = createKeyCloakUser(email);
        return keyCloak.getUsersResource().create(user);
    }

    /**
     * passwordCredential 설정
     * @param password
     * @return
     */
    public CredentialRepresentation getPasswordCred(String password) {
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false); // 일시적인
        passwordCred.setType(CredentialRepresentation.PASSWORD); // 증명 타입
        passwordCred.setValue(password);
        return passwordCred;
    }

    /**
     * 유저가 가지고 있는 그룹의 아이디
     * @param selectStr
     * @param isLoginId
     * @return
     */
    public List<String> userGroupIdList(String selectStr , boolean isLoginId) throws RestException {
        UserResource userResource = userResourceFind(selectStr ,isLoginId);
        return userResource.groups().stream().map(GroupRepresentation::getId).collect(Collectors.toList());
    }

    /**
     * 유저가 가지고 있는 그룹의 이름
     * @param selectStr
     * @param isLoginId
     * @return
     */
    public List<String> userGroupNameList(String selectStr , boolean isLoginId) throws RestException {
        UserResource userResource = userResourceFind(selectStr ,isLoginId);
        return userResource.groups().stream().map(GroupRepresentation::getName).collect(Collectors.toList());
    }

    /**
     * 로그인 아이디 또는 아이디 값으로 리소스 가져오기
     * @param selectStr
     * @param isLoginId
     * @return
     * @throws NotFoundException
     */
    private UserResource userResourceFind(String selectStr , boolean isLoginId) throws RestException {
        if (isLoginId){
            UserRepresentation userRepresentation = keyCloakUserFindByLoginId(selectStr).get(0);
            return keyCloak.getUsersResource().get(userRepresentation.getId());
        } else {
            return keyCloakUserInfoFindById(selectStr);
        }
    }
}
