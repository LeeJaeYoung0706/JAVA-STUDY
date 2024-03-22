package com.example.security.filter;

import com.example.security.dto.UserDto;
import com.example.security.signature.SecuritySigner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.jwk.JWK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 토큰을 발행하는 1차적인 필터
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final SecuritySigner securitySigner;
    private final JWK jwk;

    public JwtAuthenticationFilter(SecuritySigner securitySigner, JWK jwk) {
        this.securitySigner = securitySigner;
        this.jwk = jwk;
    }

    // 인증 처리를 위한 코드를 작성
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // 로그인 아이디와 패스워드가 json 형태로 오기 때문에 그 것을 오브젝트형태로 변환하기 위해 필요함
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            // 인풋 스트림을 객체로 변환
            UserDto userDto = objectMapper.readValue(request.getInputStream(), UserDto.class);

            log.info("test = {}" , userDto.toString() );
            // test = UserDto(username=user, password=1234)

            // 인증하는 과정
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());

            // 인증
            return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 인증 성공 이후의 플로우
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 시큐리티 컨텍스트에 인증객체 등록
        // SecurityContextHolder.getContext().setAuthentication(authResult);
        // 성공이후의 작업
        // getSuccessHandler().onAuthenticationSuccess(request , response , authResult);

        // 토큰 발행하는 로직

        User principal = (User) authResult.getPrincipal();
        try {
            String token = securitySigner.getToken(principal, jwk);
            response.addHeader( "Authorization" , "Bearer " + token);
            Cookie cookie = new Cookie("Authorization" , token);
            response.addCookie(cookie);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
