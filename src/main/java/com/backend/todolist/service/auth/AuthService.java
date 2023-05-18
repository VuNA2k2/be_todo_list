package com.backend.todolist.service.auth;

import com.backend.todolist.dto.logindto.LoginInputDto;
import com.backend.todolist.dto.logindto.LoginOutputDto;
import com.backend.todolist.dto.registerdto.RegisterInputDto;
import com.backend.todolist.dto.registerdto.RegisterOutputDto;
import com.backend.todolist.entity.UserDetailEntity;

public interface AuthService {
    LoginOutputDto login(LoginInputDto loginInputDto);
    RegisterOutputDto register(RegisterInputDto registerInputDto);
    LoginOutputDto refreshToken(String refreshToken);
}
