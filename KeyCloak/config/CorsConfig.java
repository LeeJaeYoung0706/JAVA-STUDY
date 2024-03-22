package com.keti.iam.idthub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * cors 설정
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    private final String CORS_IAM_WEB_BASE_URL;
    private final String CORS_KEYClOAK_BASE_URL;
    public CorsConfig(@Value("${cors.iamWeb}") String CORS_IAM_WEB_BASE_URL, @Value("${cors.keycloak}") String CORS_KEYClOAK_BASE_URL) {
        this.CORS_IAM_WEB_BASE_URL = CORS_IAM_WEB_BASE_URL;
        this.CORS_KEYClOAK_BASE_URL = CORS_KEYClOAK_BASE_URL;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(CORS_IAM_WEB_BASE_URL, CORS_KEYClOAK_BASE_URL)
                .allowedMethods("OPTIONS", "GET", "POST", "PATCH", "DELETE")
                .allowCredentials(true)
                .allowedHeaders("*");
    }

}
