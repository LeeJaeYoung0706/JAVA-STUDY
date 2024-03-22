package com.keti.iam.idthub.util.exception;

import com.keti.iam.idthub.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionAdvisor {

    @ExceptionHandler(RestException.class) @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> handleRESTException(RestException e) {
        return Response.builder(e.getMessage() , e.getCode()).build();
    }


    @ExceptionHandler(UsernameNotFoundException.class) @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> handleUsernameNotFoundException(UsernameNotFoundException e) {
        return Response.builder(e.getMessage() , ErrorCode.NOT_FOUND_USER.getValue()).build();
    }

    @ExceptionHandler(IllegalStateException.class) @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> handleIllegalStateException(IllegalStateException e) {
        return Response.builder(e.getMessage() , ErrorCode.UNKNOWN.getValue()).build();
    }


    @ExceptionHandler(IllegalArgumentException.class) @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return Response.builder(e.getMessage() , ErrorCode.REQUIRED_PARAM.getValue()).build();
    }


    @ExceptionHandler(Exception.class) @Order(value = Ordered.HIGHEST_PRECEDENCE)
    public Response<?> handleException(Exception e) {
        log.error("error name = {} " , e.getClass().getSimpleName());
        log.error("error message = {} " , e.getMessage());
        return Response.builder(e.getMessage() , ErrorCode.UNKNOWN.getValue()).build();
    }


}