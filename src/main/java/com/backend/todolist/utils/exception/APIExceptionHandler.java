package com.backend.todolist.utils.exception;

import com.backend.todolist.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(OK)
    public Response<ErrorEntity> handleAllException(Exception e) {
        return new Response<>(new ErrorEntity("500", e.getMessage()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(OK)
    public Response<ErrorEntity> handleAuthenticationException(Exception e) {
        return new Response<>(new ErrorEntity("401", e.getMessage()));
    }
    @ExceptionHandler(RestException.class)
    @ResponseStatus(OK)
    public Response<ErrorEntity> handleRestException(RestException e) {
        return new Response<>(new ErrorEntity(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(OK)
    public Response<ErrorEntity> handleBindException(BindException e) {
        return new Response<>(new ErrorEntity("400", e.getMessage()));
    }

    // TODO: Add more exception handler here
}
