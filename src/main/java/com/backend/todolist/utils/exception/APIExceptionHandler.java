package com.backend.todolist.utils.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorEntity handleAllException(Exception e) {
        return new ErrorEntity("500", e.getMessage());
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(OK)
    public ErrorEntity handleAuthenticationException(Exception e) {
        return new ErrorEntity("401", e.getMessage());
    }
    @ExceptionHandler(RestException.class)
    @ResponseStatus(OK)
    public ErrorEntity handleRestException(RestException e) {
        return new ErrorEntity(e.getCode(), e.getMessage());
    }

    // TODO: Add more exception handler here
}
