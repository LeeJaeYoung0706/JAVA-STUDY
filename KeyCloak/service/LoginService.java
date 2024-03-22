package com.keti.iam.idthub.service;

import com.keti.iam.idthub.util.URLConnectionTemplate;
import com.keti.iam.idthub.util.exception.RestException;
import com.keti.iam.idthub.util.keycloak.KeyCloakRole;
import com.keti.iam.idthub.util.keycloak.KeyCloakUser;
import com.keti.iam.idthub.util.stringBuilder.StringBuilderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final KeyCloakUser keyCloakUser;
    private final KeyCloakRole keyCloakRole;

    public String accessTokenFindByCode(String code) throws IOException {

        URLConnectionTemplate urlConnectionTemplate = new URLConnectionTemplate() {
            @Override
            protected void call(HttpURLConnection httpURLConnection, String formData) throws ProtocolException {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                httpURLConnection.setRequestProperty("Content-Length", Integer.toString(formData.length()));
                httpURLConnection.setUseCaches(false);
            }
        };
        String formData = StringBuilderUtil.build("client_id=test&client_secret=2ocjAn7aoWmSPirKbqHvdiVfagNYTpKs",
                "&code=", code, "&redirect_uri=http://localhost:8080/login", "&grant_type=authorization_code");
        return urlConnectionTemplate.urlExecute("http://localhost:8090/realms/test/protocol/openid-connect/token" , true , formData , true );


        //HttpURLConnection conn = URLUtil.createConnection("http://localhost:8090/realms/keti_security/protocol/openid-connect/token");

//        conn.setRequestMethod("POST");
//        conn.setDoOutput(true);
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        conn.setRequestProperty("Content-Length", Integer.toString(formData.length()));
//        conn.setUseCaches(false);

//        try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
//            dos.writeBytes(formData);
//        }

//        try (BufferedReader br = new BufferedReader(new InputStreamReader(
//                conn.getInputStream())))
//        {
//            String line;
//            StringBuilder token = new StringBuilder();
//            while ((line = br.readLine()) != null) {
//                token.append(line);
//            }
//            return token.toString();
//        }

    }

    /**
     * 로그인 시 유저 Role 주기
     * @param user
     */
    public void setUserRole(OAuth2User user) throws RestException {
        Object sub = user.getAttribute("sub");
        if(sub != null){
            String id = String.valueOf(sub);
            //keyCloakRole.userRoleSelect(id); 동일한 권한 줘도 중복추가같은 오류 없음.
            keyCloakRole.userRoleSetting(id , "USER_001");
        } else {
            throw new IllegalArgumentException("유저의 정보가 일치하지 않습니다.");
        }
    }


    public String accessTokenFindByUserInfo(String accessToken) throws IOException {
        URLConnectionTemplate urlConnectionTemplate = new URLConnectionTemplate() {
            @Override
            protected void call(HttpURLConnection conn , String formData) throws ProtocolException {
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", formData);
                conn.setUseCaches(false);
            }
        };
        String formData = StringBuilderUtil.build("Bearer ", accessToken.trim());
        return urlConnectionTemplate
                .urlExecute("http://localhost:8090/realms/test/protocol/openid-connect/userinfo",
                        true, formData , false);
    }
}
