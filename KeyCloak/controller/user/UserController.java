package com.keti.iam.idthub.controller.user;

import com.keti.iam.idthub.dto.user.UserInfoResponseDto;
import com.keti.iam.idthub.dto.user.UserSaveRequestDto;
import com.keti.iam.idthub.service.user.UserService;
import com.keti.iam.idthub.util.exception.RestException;
import com.keti.iam.idthub.util.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/init/save")
    @ApiOperation(value = "첫 로그인 회원 저장")
    public Response<String> userInitSave(@Valid @RequestBody
                                             @ApiParam(name = "첫 로그인 회원 저장 요청 객체")
                                                UserSaveRequestDto userSaveRequestDto ,
                                         @ApiIgnore
                                            Authentication authentication,
                                         BindingResult bindingResult) throws RestException {
        if(bindingResult.hasErrors())
            throw new RestException("IllegalArgumentException" , 403);
        return Response.<String>builder("OK" , 200)
                .total(userService.initSave(userSaveRequestDto, authentication))
                .build();
    }


    @GetMapping("/userJoinCount")
    @ApiOperation(value = "처음 가입 회원인지 체크")
    public Response<Boolean> userCount(
            @ApiIgnore
            Authentication authentication){
        return Response.<Boolean>builder("OK", 200)
                .data(userService.userCount(authentication.getName()))
                .total(1)
                .build();
    }


    @GetMapping("/userInfoFindById")
    @ApiOperation(value = "토큰으로 접근 시 유저 아이디로 유저 정보 찾기")
    public Response<UserInfoResponseDto> userInfoFindById(
                                                          @ApiIgnore
                                                          Authentication authentication
                                                      ){
        return Response.<UserInfoResponseDto>builder("OK", 200)
                .data(userService.userInfoFindById(authentication.getName()))
                .total(1)
                .build();
    }
}
