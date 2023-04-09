package com.backend.todolist.service.auth;

import com.backend.todolist.dto.logindto.LoginInputDto;
import com.backend.todolist.dto.logindto.LoginOutputDto;

public interface AuthService {
    LoginOutputDto login(LoginInputDto loginInputDto);

}
