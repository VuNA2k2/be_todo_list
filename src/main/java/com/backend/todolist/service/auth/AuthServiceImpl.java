package com.backend.todolist.service.auth;

import com.backend.todolist.dto.logindto.LoginInputDto;
import com.backend.todolist.dto.logindto.LoginOutputDto;
import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.utils.provider.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public LoginOutputDto login(LoginInputDto loginInputDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginInputDto.getUserName(),
                        loginInputDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((UserDetailEntity) authentication.getPrincipal());
        return new LoginOutputDto(jwt, jwt);
    }
}
