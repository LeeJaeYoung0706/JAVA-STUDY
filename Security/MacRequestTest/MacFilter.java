package com.example.security.filter;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Slf4j
public class JwtAuthorizationMACFilter extends OncePerRequestFilter {

    private OctetSequenceKey octetSequenceKey;

    public JwtAuthorizationMACFilter(OctetSequenceKey octetSequenceKey) {
        this.octetSequenceKey = octetSequenceKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 형식에 대한 유효성 검증
        String authorization = request.getHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer ")){
            filterChain.doFilter(request , response);
        } else {
            String token = authorization.replace("Bearer ", "");
            // header , payload , signature


            try {
                SignedJWT parse = SignedJWT.parse(token);
                // 검증
                MACVerifier macVerifier = new MACVerifier(octetSequenceKey.toSecretKey());
                log.info("octetSequenceKey.toSecretKey() = {}" , octetSequenceKey.toSecretKey());
                boolean verify = parse.verify(macVerifier);
                
                // 검증이 완료가 되면
                if(verify){
                    JWTClaimsSet jwtClaimsSet = parse.getJWTClaimsSet();
                    String username = jwtClaimsSet.getClaim("username").toString();
                    List<String> authority = (List<String>) jwtClaimsSet.getClaim("authority");

                    if(username != null){
                        UserDetails user = User.withUsername(username).password(UUID.randomUUID().toString()).authorities(authority.get(0)).build();
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            } catch (ParseException | JOSEException e) {
                throw new RuntimeException(e);
            }

            filterChain.doFilter(request , response);
        }





    }
}
