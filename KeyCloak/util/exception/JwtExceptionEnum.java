package com.keti.iam.idthub.util.exception;

import lombok.Getter;
import java.util.Arrays;
import java.util.List;

/**
 * jwt exception 분기처리 enum
 */
@Getter
public enum JwtExceptionEnum {

    INVALID_TOKEN("Invalid Token Error" ,
            ErrorCode.INVALID_TOKEN.getValue() ,
            Arrays.asList("An error occurred while attempting to decode the Jwt: Signed JWT rejected" ,
                            "An error occurred while attempting to decode the Jwt: Malformed payload" ,
                            "An error occurred while attempting to decode the Jwt: Invalid" ,
                            "Bearer token is malformed")),
    EXPIRE_TOKEN("Expired Token Error" ,
            ErrorCode.EXPIRED_TOKEN.getValue() , List.of("An error occurred while attempting to decode the Jwt: Jwt expired")),
    NO_AUTHENTICATION("UnAuthentication Error" ,
            ErrorCode.UNAUTHORIZED.getValue() , List.of("Full authentication")),
    ACCESS_DENIED("Access Denied Error" , ErrorCode.ACCESS_DENIED.getValue()
    , List.of("Access is denied") ),
    DEFAULT( "UnKnown Error" ,
            ErrorCode.UNAUTHORIZED.getValue(), List.of("IllegalStateException" , "null"));
    private final String ERROR;
    private final int code;
    private final List<String> ERROR_REASON;

    JwtExceptionEnum(String ERROR, int code, List<String> ERROR_REASON) {
        this.ERROR = ERROR;
        this.code = code;
        this.ERROR_REASON = ERROR_REASON;
    }

    public static JwtExceptionEnum findByErrorReason(String message){
        return Arrays.stream(JwtExceptionEnum.values())
                .filter(exceptionEnum -> hasMessage(exceptionEnum , message))
                .findAny()
                .orElse(JwtExceptionEnum.DEFAULT);
    }

    private static boolean hasMessage(JwtExceptionEnum exceptionEnum , String message){
        return exceptionEnum.ERROR_REASON.stream()
                .anyMatch(message::startsWith);
    }
}
