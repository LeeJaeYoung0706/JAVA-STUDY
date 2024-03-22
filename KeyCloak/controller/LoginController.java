package com.keti.iam.idthub.controller;

import com.keti.iam.idthub.service.LoginService;
import com.keti.iam.idthub.util.exception.RestException;
import com.keti.iam.idthub.util.response.Response;
import com.keti.iam.idthub.util.stringBuilder.StringBuilderUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public void login(Authentication authentication,
                      @RequestParam(required = false) String code ,
                      @RequestParam(required = false) String access_token ,
                      HttpServletResponse response,
                      @AuthenticationPrincipal OAuth2User user
    ) throws IOException, RestException {
//        if(authentication != null) {
//            loginService.setUserRole(user);
//        } else {
//            throw new UsernameNotFoundException("회원을 찾을 수 없습니다.");
//        }
        log.info("test = {}" , code);
        if(code != null){
            if(access_token == null)
                access_token = loginService.accessTokenFindByCode(code);
            JsonParser jsonParser = new JacksonJsonParser();
            Map<String, Object> stringObjectMap = jsonParser.parseMap(access_token);
            Object access_token1 = stringObjectMap.get("access_token");
            response.sendRedirect(StringBuilderUtil.build("http://localhost:8081/login/sucess?accessToken=", access_token1.toString()));
        } else {
            response.sendRedirect("http://localhost:8081/");
        }
    }


    /**
     * 토큰으로 유저 정보 가져오기
     * @param token
     * @throws IOException
     */
    @GetMapping("/token/test")
    public Response<String> tokenTest(@RequestParam String token) throws IOException {
        return Response.<String>builder("회원 조회 성공" , 200)
                .data(loginService.accessTokenFindByUserInfo(token))
                .build();
    }
    /**
     * 토큰으로 유저 정보 가져오기
     * @param token
     * @throws IOException
     */
    @PatchMapping("/test")
    public ResponseEntity test(@RequestBody(required = false) bodyParam test) throws IOException {
        log.info("test1111111   ={} " , test.getTest() );
        //return Response.<String>builder("회원 조회 성공" , 403)
          //     .data("test")
           //    .build();
        return ResponseEntity.status(402).build();

    }


}

@Getter
@Setter
class bodyParam {
    String test;
}
