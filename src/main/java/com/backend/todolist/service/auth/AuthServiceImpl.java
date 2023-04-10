package com.backend.todolist.service.auth;

import com.backend.todolist.dto.accountdto.AccountInputDto;
import com.backend.todolist.dto.logindto.LoginInputDto;
import com.backend.todolist.dto.logindto.LoginOutputDto;
import com.backend.todolist.dto.registerdto.RegisterInputDto;
import com.backend.todolist.dto.registerdto.RegisterOutputDto;
import com.backend.todolist.dto.userdto.UserDetailOutputDto;
import com.backend.todolist.dto.userdto.UserInputDto;
import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.service.account.AccountMapper;
import com.backend.todolist.service.account.AccountService;
import com.backend.todolist.service.jwt.JwtService;
import com.backend.todolist.service.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final AuthMapper authMapper;
    private final AccountService accountService;
    private final UserService userService;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService tokenProvider, AuthMapper authMapper, AccountService accountService, UserService userService, AccountMapper accountMapper, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = tokenProvider;
        this.authMapper = authMapper;
        this.accountService = accountService;
        this.userService = userService;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public LoginOutputDto login(LoginInputDto loginInputDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginInputDto.getUsername(),
                        loginInputDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateToken((UserDetailEntity) authentication.getPrincipal());
        return new LoginOutputDto(jwt, jwt);
    }

    @Override
    public RegisterOutputDto register(RegisterInputDto registerInputDto) {
        AccountInputDto accountInputDto = authMapper.getAccountInputDtoFromRegisterInputDto(registerInputDto);
        UserInputDto userInputDto = authMapper.getUserInputDtoFromRegisterInputDto(registerInputDto);
        UserDetailOutputDto userDetailOutputDto = userService.createUser(userInputDto);
        accountInputDto.setUserId(userDetailOutputDto.getUserId());
        accountInputDto.setPassword(passwordEncoder.encode(registerInputDto.getPassword()));
        accountService.createAccount(accountInputDto);
        accountMapper.getEntityFromInput(accountInputDto);
        return authMapper.getRegisterOutputDtoFromUserDetailOutputDto(userDetailOutputDto);
    }


}
