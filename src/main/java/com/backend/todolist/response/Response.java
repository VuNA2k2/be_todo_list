package com.backend.todolist.response;

import lombok.Builder;

@Builder
public class Response<T> {
    private String code;
    private String message;
    private T data;
}
