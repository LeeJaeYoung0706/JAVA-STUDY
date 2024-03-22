package com.keti.iam.idthub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.Collections;
import java.util.List;


@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Value("${spring.jwt.header}")
    private String SPRING_JWT_HEADER;

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("KETI IAM REST API")
                .description("KETI IAM REST API Documentation")
                .version("1.0")
                .build();
    }


    @Bean
    public Docket userSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("유저 API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.keti.iam.idthub.controller"))
                .paths(PathSelectors.ant("/user/**"))
                .build()
                .securitySchemes(List.of(apiKey()))
                .securityContexts(Collections.singletonList(userSecurityContext()))
                .useDefaultResponseMessages(false);
    }

    /**
     * 인증 권한 테스트 용 스웨거
     * @return
     */
    @Bean
    public Docket authTestSwagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("권한 테스트 API")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.keti.iam.idthub.controller"))
                .paths(PathSelectors.ant("/"))
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", SPRING_JWT_HEADER, "header");
    }


    private SecurityContext userSecurityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.ant("/user/**"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = {authorizationScope};

        return List.of(new SecurityReference("JWT", authorizationScopes));
    }
}
