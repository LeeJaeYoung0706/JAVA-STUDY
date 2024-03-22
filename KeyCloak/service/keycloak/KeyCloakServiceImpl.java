package com.keti.iam.idthub.service.keycloak;

import com.keti.iam.idthub.util.exception.RestException;
import com.keti.iam.idthub.util.keycloak.KeyCloakClient;
import com.keti.iam.idthub.util.keycloak.KeyCloakRole;
import com.keti.iam.idthub.util.keycloak.KeyCloakUser;
import com.keti.iam.idthub.util.stringBuilder.StringBuilderUtil;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class KeyCloakServiceImpl implements KeyCloakService{

    private final KeyCloakUser keyCloakUser;
    private final KeyCloakClient keyCloakClient;
    private final KeyCloakRole keyCloakRole;

    @Override
    public String keyCloakClientGetSecret() throws RestException {
        ClientRepresentation clientRepresentation = keyCloakClient.clientFind();
        return clientRepresentation.getSecret();
    }
//
//    public String keyCloakUserRoles(Authentication authentication) throws RestException {
//        List<RoleRepresentation> roleRepresentations = keyCloakRole.userRoleSelect(authentication.getName());
//        for (RoleRepresentation roleRepresentation : roleRepresentations) {
//            String name = roleRepresentation.getName();
//        }
//        StringBuilderUtil.build()
//
//
//    }
}
