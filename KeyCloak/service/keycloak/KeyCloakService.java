package com.keti.iam.idthub.service.keycloak;

import com.keti.iam.idthub.util.exception.RestException;

public interface KeyCloakService {

    String keyCloakClientGetSecret() throws RestException;
}
