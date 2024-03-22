package com.keti.iam.idthub.config;


import com.keti.iam.idthub.util.security.CustomAccessDeniedHandler;
import com.keti.iam.idthub.util.security.CustomAuthenticationEntryPoint;
import com.keti.iam.idthub.util.security.CustomJwtAuthenticationConverter;
import com.keti.iam.idthub.util.security.ValidationFailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private OAuth2ResourceServerProperties properties;

    @Autowired
    public void setProperties(OAuth2ResourceServerProperties properties) {
        this.properties = properties;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests(request ->
                request.antMatchers("/user/findByUserId").hasRole("USER")
                        .antMatchers("/login" , "/test").permitAll()
                        .anyRequest().authenticated());
//        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt); 기본 키클록 설정 사용 + 필터
        http.oauth2ResourceServer(oauth2 -> {
            oauth2.jwt(jwt ->
                         jwt.decoder(
                                NimbusJwtDecoder.withJwkSetUri(properties.getJwt().getJwkSetUri()).jwsAlgorithm(SignatureAlgorithm.RS512).build())
//                            .jwtAuthenticationConverter(customJwtAuthenticationConverter())
                            .and()
                            .authenticationEntryPoint(customAuthenticationEntryPoint())
            );
        });
        http.exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler());
        return http.build();
    }

    /**
     * 인가 실패 핸들러
     * @return
     */
    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler(){
        return new CustomAccessDeniedHandler(new ValidationFailResponse());
    }

    /**
     * 인증 실패
     * @return
     */
    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint(){
        return new CustomAuthenticationEntryPoint(new ValidationFailResponse());
    }

    /**
     * 키클록 client role 권한 맵핑
     * @return
     */
//    @Bean CustomJwtAuthenticationConverter customJwtAuthenticationConverter(){
//        return new CustomJwtAuthenticationConverter();
//    }

    /**
     * ignore
     * @return
     */
    @Bean
    public WebSecurityCustomizer customWebSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/csrf" ,  "/" ,"/swagger-resources/**" ,"/swagger-ui.html/**" , "/v2/api-docs/**" , "/webjars/**");
    }

}
