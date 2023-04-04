package com.backend.todolist.response;

import com.backend.todolist.utils.exception.ErrorEntity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Response<T> {
    private ErrorEntity error;
    private T data;

    public Response(ErrorEntity error) {
        this.error = error;
    }

    public Response(ErrorEntity error, T data) {
        this.error = error;
        this.data = data;
    }

    public Response() {
    }

    public static <T> Response<T> success(T data) {
        return new Response(new ErrorEntity("success", "Thành công"), data);
    }

    public static <T> Response<T> success() {
        return new Response(new ErrorEntity("success", "Thành công"));
    }

    public static <T> Response<T> fail(ErrorEntity error) {
        return new Response(error);
    }
}
