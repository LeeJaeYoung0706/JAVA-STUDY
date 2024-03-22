package com.example.security.signature;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.security.core.userdetails.UserDetails;

public class MACSecuritySigner extends SecuritySigner{

    @Override
    public String getToken(UserDetails user, JWK jwk) throws JOSEException {

        // MAC 서명인자를 가져옴
        MACSigner macSigner = new MACSigner( ((OctetSequenceKey) jwk).toSecretKey());
        // 부모 SecuritySigner 에서 공통적으로 처리
        return getJwtTokenInternal(macSigner , user , jwk);
    }
}
