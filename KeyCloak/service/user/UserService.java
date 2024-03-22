package com.keti.iam.idthub.service.user;

import com.keti.iam.idthub.dto.user.UserInfoResponseDto;
import com.keti.iam.idthub.dto.user.UserSaveRequestDto;
import com.keti.iam.idthub.util.exception.RestException;
import org.springframework.security.core.Authentication;

public interface UserService {

    int initSave(UserSaveRequestDto userSaveRequestDto , Authentication authentication) throws RestException;
    UserInfoResponseDto userInfoFindById(String id);
    Boolean userCount(String id);
}
