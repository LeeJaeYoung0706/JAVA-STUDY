package com.keti.iam.idthub.service.user;

import com.keti.iam.idthub.aop.annotations.Timer;
import com.keti.iam.idthub.dto.user.UserInfoResponseDto;
import com.keti.iam.idthub.dto.user.UserSaveRequestDto;
import com.keti.iam.idthub.mapper.UserMapper;
import com.keti.iam.idthub.util.exception.ErrorCode;
import com.keti.iam.idthub.util.exception.RestException;
import com.keti.iam.idthub.util.keycloak.KeyCloakUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService{

    private final UserMapper userMapper;
    private final KeyCloakUser keyCloakUser;

    /**
     *  처음 로그인 한 사용자 DB 에 저장하는 로직
     * @param userSaveRequestDto
     * @param authentication
     * @return
     * @throws RestException
     */
    @Override
    public int initSave(UserSaveRequestDto userSaveRequestDto , Authentication authentication) throws RestException {
        String keyCloakId = authentication.getName();
        if(userMapper.countFindById(keyCloakId) > 0)

            throw new RestException("ExistMemberException" , ErrorCode.RESULT_EXIST.getValue());
        else {
            userSaveRequestDto.setId(keyCloakId);
            String email = keyCloakUser.keyCloakUserInfoFindById(keyCloakId).
                    toRepresentation().getEmail();
            userSaveRequestDto.setEmail(email);
            return userMapper.initSave(userSaveRequestDto);
        }
    }

    /**
     * 키클록 아이디로 유저 정보 검색
     * @param id
     * @return
     */
    @Override
    @Timer
    public UserInfoResponseDto userInfoFindById(String id) {
        return userMapper.userInfoFindById(id)
                .orElseThrow( () -> {throw new UsernameNotFoundException("UsernameNotFoundException");});
    }

    @Override
    public Boolean userCount(String id) {
        return userMapper.countFindById(id) >= 1;
    }
}
