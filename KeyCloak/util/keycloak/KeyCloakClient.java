package com.keti.iam.idthub.util.keycloak;


import com.keti.iam.idthub.util.exception.RestException;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeyCloakClient {

    private final KeyCloakResourceAPI keyCloak;

    @Value("${keycloak.resource}")
    private String clientId;

    @Autowired
    public KeyCloakClient(KeyCloakResourceAPI keyCloak) {
        this.keyCloak = keyCloak;
    }

    /**
     *  KeyCloak Client Find  # 키클락에 등록된 클라이언트 찾기 contains 로 list 리턴
     */
    public ClientRepresentation clientFind() throws RestException {
        try {
            return keyCloak.getRealmResource().clients().findByClientId(clientId).get(0);
        } catch (Exception e){
            throw new RestException("KeyCloak Error Client Not Found" , 404);
        }
    }

}