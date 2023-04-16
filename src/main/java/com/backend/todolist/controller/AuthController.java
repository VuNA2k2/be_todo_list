package com.backend.todolist.controller;

import com.backend.todolist.dto.logindto.LoginInputDto;
import com.backend.todolist.dto.logindto.LoginOutputDto;
import com.backend.todolist.dto.registerdto.RegisterInputDto;
import com.backend.todolist.dto.registerdto.RegisterOutputDto;
import com.backend.todolist.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    LoginOutputDto login(@RequestBody @Valid LoginInputDto loginInputDto) {
        return authService.login(loginInputDto);
    }

    @PostMapping("/register")
    RegisterOutputDto register(@RequestBody @Valid RegisterInputDto registerInputDto) {
        return authService.register(registerInputDto);
    }
}
