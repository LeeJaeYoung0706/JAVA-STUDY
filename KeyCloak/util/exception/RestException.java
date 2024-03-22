package com.keti.iam.idthub.util.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RestException extends Exception{
    private final int code;
    private final String message;
    @Builder
    public RestException(String message , int code) {
        super();
        this.code = code;
        this.message = message;
    }
}
