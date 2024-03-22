package com.keti.iam.idthub.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyCloakConfig {
    private final String KEYCLOAK_URL;
    private final String KEYCLOAK_REALM;
    private final String KEYCLOAK_CLIENT_ID;
    private final String KEYCLOAK_CLIENT_SECRET;

    public KeyCloakConfig(@Value("${keycloak.auth-server-url}") String KEYCLOAK_URL,
                          @Value("${keycloak.realm}") String KEYCLOAK_REALM,
                          @Value("${keycloak.resource}") String KEYCLOAK_CLIENT_ID,
                          @Value("${keycloak.credentials.secret}") String KEYCLOAK_CLIENT_SECRET) {
        this.KEYCLOAK_URL = KEYCLOAK_URL;
        this.KEYCLOAK_REALM = KEYCLOAK_REALM;
        this.KEYCLOAK_CLIENT_ID = KEYCLOAK_CLIENT_ID;
        this.KEYCLOAK_CLIENT_SECRET = KEYCLOAK_CLIENT_SECRET;
    }

    /**
     * 시큐리티에 빈 설정하면 순환참조 문제 발생
     * @return
     */
    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    /*
     *  Keycloak 서버와 통신하기 위한 클라이언트 빌더
     * */
    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(KEYCLOAK_URL)
                .realm(KEYCLOAK_REALM)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .clientId(KEYCLOAK_CLIENT_ID)
                .clientSecret(KEYCLOAK_CLIENT_SECRET)
                .build();
    }

}
