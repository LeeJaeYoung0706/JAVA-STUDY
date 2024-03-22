package com.keti.iam.idthub.mapper;

import com.keti.iam.idthub.dto.user.UserInfoResponseDto;
import com.keti.iam.idthub.dto.user.UserSaveRequestDto;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMapper {

    int initSave(UserSaveRequestDto userSaveRequestDto);

    int countFindById(String id);
    Optional<UserInfoResponseDto> userInfoFindById(String id);
}
