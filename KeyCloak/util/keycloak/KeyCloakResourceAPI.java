package com.keti.iam.idthub.util.keycloak;


import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;

@Component
public class KeyCloakResourceAPI {

    private final Keycloak keycloak;
    @Value("${keycloak.realm}")
    private String realm;

    public KeyCloakResourceAPI(Keycloak keycloak) {
        this.keycloak = keycloak;
    }

    public RealmResource getRealmResource() {
        return keycloak.realm(realm);
    }

    public UsersResource getUsersResource() {
        return getRealmResource().users();
    }

    /**
     * keyCloak createId   # 아이디 생성된 것 가져오기
     * @param response
     * @return
     */
    public static String getCreatedId(Response response) {
        URI location = response.getLocation();
        if (!response.getStatusInfo().equals(Response.Status.CREATED)) {
            Response.StatusType statusInfo = response.getStatusInfo();
            response.bufferEntity();
            String body = response.readEntity(String.class);
            throw new WebApplicationException("Create method returned status "
                    + statusInfo.getReasonPhrase() + " (Code: " + statusInfo.getStatusCode() + "); expected status: Created (201). Response body: " + body, response);
        }
        if (location == null) {
            return null;
        }
        String path = location.getPath();
        return path.substring(path.lastIndexOf('/') + 1);
    }
}
