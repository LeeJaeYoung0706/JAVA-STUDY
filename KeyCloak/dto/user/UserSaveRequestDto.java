package com.keti.iam.idthub.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Alias("userSaveRequestDto")
@ApiModel(value = "유저 저장 요청 전송 객체")
public class UserSaveRequestDto {

    @ApiModelProperty(value = "첫 로그인 요청 참거짓 : true" , notes = "첫 로그인 요청 참거짓" , required = true , example = "true")
    @NotBlank
    private boolean firstLoginRequest; // 첫 로그인 요청
    @ApiModelProperty( required = false , hidden=true)
    private String id; // 키클록 아이디
    @ApiModelProperty( required = false , hidden=true)
    private String email; // 키클록 이메일
    @ApiModelProperty(value = "이름 : 이재영" , notes = "이름" , required = true , example = "이재영")
    @NotBlank
    private String name; // 이름
    @ApiModelProperty(value = "회사이름 : 뚠뚠이김밥" , notes = "회사 이름" , example = "뚠뚠이김밥")
    private String company; // 회사
    @ApiModelProperty(value = "위치 : 테스트위치" , notes = "위치", example = "테스트 위치")
    private String location; // 위치
    @ApiModelProperty(value = "홈페이지 : www.test.co.kr" , notes = "홈페이지"  , example = "www.test.co.kr")
    private String webSite; // 홈페이지
}
