package com.example.security.config;


import com.example.security.filter.JwtAuthenticationFilter;
import com.example.security.filter.JwtAuthorizationMACFilter;
import com.example.security.signature.MACSecuritySigner;
import com.example.security.signature.SecuritySigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
public class SecurityConfig {


    private MACSecuritySigner macSecuritySigner;
    private OctetSequenceKey octetSequenceKey;

    @Autowired
    public void setMacSecuritySigner(MACSecuritySigner macSecuritySigner) {
        this.macSecuritySigner = macSecuritySigner;
    }
    @Autowired
    public void setOctetSequenceKey(OctetSequenceKey octetSequenceKey) {
        this.octetSequenceKey = octetSequenceKey;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests( request -> request.antMatchers("/").permitAll().anyRequest().authenticated());
        http.addFilterBefore(jwtAuthenticationFilter(macSecuritySigner , octetSequenceKey) , UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthorizationMACFilter(octetSequenceKey) , UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public JwtAuthorizationMACFilter jwtAuthorizationMACFilter(OctetSequenceKey octetSequenceKey) {
        return new JwtAuthorizationMACFilter(octetSequenceKey);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(SecuritySigner securitySigner , JWK jwk) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(securitySigner , jwk);

        /**
         *  AuthenticationManager 가 필요한 이유는
         *  JwtAuthenticationFilter 가 상속받는 UsernamePasswordAuthenticationFilter 가
         *  AbstractAuthenticationProcessingFilter 를 상속 받는데
         *  private AuthenticationManager authenticationManager; 여기 처럼 매개변수로 필요하기 때문이다.
         */
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager(null));
        return jwtAuthenticationFilter;
    }


    // 테스트용 사용자 계정 생성
    @Bean
    public UserDetailsService userDetailsService(){
       UserDetails build = User.withUsername("user")
               .password(passwordEncoder().encode("1234"))
               .authorities("ROLE_USER").build();
       // 인 메모리 형식으로 쓰고 지울 것이라
       return new InMemoryUserDetailsManager(build);
    }

    // 패스워드 빈
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();  // 테스트용
    }

//    // 패스워드 빈
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
