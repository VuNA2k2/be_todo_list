package com.backend.todolist.response;

import lombok.Builder;

@Builder
public class Response<T> {
    private String code;
    private String message;
    private T data;

    public Response(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
