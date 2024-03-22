package com.keti.iam.idthub.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("userInfoResponseDto")
public class UserInfoResponseDto {

    private String id; // 키클록 아이디
    private String email; // 키클록 이메일
    private String name; // 이름
    private String company; // 회사
    private String location; // 위치
    private String webSite; // 홈페이지
    private boolean deactivated; // 사용 여부
    private boolean is_join; // 처음 회원ㄱ ㅏ입페이지 작성 여부
}
