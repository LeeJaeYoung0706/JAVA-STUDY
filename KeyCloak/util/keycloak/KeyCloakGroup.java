package com.keti.iam.idthub.util.keycloak;

import com.keti.iam.idthub.util.exception.RestException;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Slf4j
public class KeyCloakGroup {

    private final KeyCloakResourceAPI keyCloakResourceAPI;

    @Autowired
    public KeyCloakGroup(KeyCloakResourceAPI keyCloakResourceAPI) {
        this.keyCloakResourceAPI = keyCloakResourceAPI;
    }

    /**
     * KeyCloak Create Group  제일 상위 그룹 생성
     * @param groupName
     * @param groupPath
     * @return
     * @throws RestException
     */
    public GroupRepresentation createKeyCloakGroups(String groupName , String groupPath) throws RestException {
        GroupRepresentation groupRepresentation = createGroupRepresentation(groupName , groupPath);
        try (Response add = keyCloakResourceAPI.getRealmResource().groups().add(groupRepresentation)) {
            String groupId = KeyCloakResourceAPI.getCreatedId(add);
            groupRepresentation.setId(groupId);
            return groupRepresentation;
        } catch (Exception e) {
            throw new RestException( "KeyCloak Error : " + e.getMessage() , 404 );
        }
    }

    /**
     * 그룹 기본 값 세팅
     * @param groupName
     * @param groupPath
     * @return
     */
    protected GroupRepresentation createGroupRepresentation(String groupName , String groupPath){
        GroupRepresentation groupRepresentation = new GroupRepresentation();
        groupRepresentation.setName(groupName);
        groupRepresentation.setPath(groupPath);
        return groupRepresentation;
    }



    /**
     * KeyCloak SubGroup Create   서브 그룹 생성 -> 재귀 처리 가능
     * @param groupRepresentationParent
     * @param subGroupName
     * @param subGroupPath
     * @return
     * @throws RestException
     */
    public GroupRepresentation addSubGroup(GroupRepresentation groupRepresentationParent , String subGroupName , String subGroupPath) throws RestException {
        GroupRepresentation groupRepresentation = createGroupRepresentation(subGroupName , subGroupPath);
        try(Response response = keyCloakResourceAPI.getRealmResource().groups().add(groupRepresentation)){
            groupRepresentation.setId(KeyCloakResourceAPI.getCreatedId(response));
            //Response responseAdd = keyCloakResourceAPI.getRealmResource().groups().group(groupRepresentationParent.getId()).subGroup(groupRepresentation);
            addSubGroupResponse(groupRepresentationParent , groupRepresentation);
            return groupRepresentation;
        } catch (Exception e) {
            throw new RestException( "KeyCloak Error : " + e.getMessage() , 404 );
        }
    }


//    public GroupRepresentation addSubGroup(GroupRepresentation groupRepresentationParent , String subGroupName , String subGroupPath) throws RestException {
//        GroupRepresentation groupRepresentation = createGroupRepresentation(subGroupName , subGroupPath);
//        try
//        {
//            Response response = keyCloakResourceAPI.getRealmResource().groups().add(groupRepresentation);
//            groupRepresentation.setId(KeyCloakResourceAPI.getCreatedId(response));
//            response = keyCloakResourceAPI.getRealmResource().groups().group(groupRepresentationParent.getId()).subGroup(groupRepresentation);
//            response.close();
//            return groupRepresentation;
//        } catch (Exception e) {
//            throw new RestException(404 , "KeyCloak Error : " + e.getMessage() );
//        }
//    }
    public void addSubGroupResponse(GroupRepresentation groupRepresentationParent , GroupRepresentation groupRepresentation) throws RestException {
        try (Response responseAdd = keyCloakResourceAPI.getRealmResource().groups().group(groupRepresentationParent.getId()).subGroup(groupRepresentation)) {
            Response.StatusType statusInfo = responseAdd.getStatusInfo();
            int statusCode = statusInfo.getStatusCode();
            if( statusCode <= 200 || statusCode > 206 ){
                throw new RestException( "KeyCloak Error " , 404);
            }
        } catch (Exception e){
            throw new RestException( "KeyCloak Error : " + e.getMessage() , 404 );
        }
    }

    /**
     * 그룹이 존재하는지 체크
     * @param id
     * @return
     */
    public boolean keyCloakGroupExist(String id) {
        List<GroupRepresentation> groups = keyCloakResourceAPI.getRealmResource().groups().groups(id, 0, 1);
        if(!groups.isEmpty()) {
            try {
                return groups.get(0).getName().equals(id);
            } catch (NotFoundException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
