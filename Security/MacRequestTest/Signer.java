package com.example.security.signature;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


// 토큰을 발행하는 클래스
public abstract class SecuritySigner {

    protected String getJwtTokenInternal(MACSigner jwsSigner, UserDetails user, JWK jwk) throws JOSEException {
        // jwt 의 header
        JWSHeader header = new JWSHeader.Builder((JWSAlgorithm) jwk.getAlgorithm()).keyID(jwk.getKeyID()).build();
        List<String> collect = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // jwt 의 payload
        JWTClaimsSet jwtClaimsSet
                = new JWTClaimsSet.Builder()
                .subject("user")
                .issuer("http://localhost:8080")
                .claim("username" , user.getUsername())
                .claim("authority" , collect)
                // 문자열로 넣어야하니까 바꿔야함
                //.claim("authority" , "ROLE_USER")
                .expirationTime(new Date( new Date().getTime() + 60 * 1000 * 30 ))
                .build();

        // jwt signature
        SignedJWT signedJWT = new SignedJWT( header , jwtClaimsSet);
        signedJWT.sign(jwsSigner);

        // 토큰으로 직렬화해서 내보냄
        return signedJWT.serialize();
    }

    public abstract String getToken(UserDetails user , JWK jwk) throws JOSEException;

}
