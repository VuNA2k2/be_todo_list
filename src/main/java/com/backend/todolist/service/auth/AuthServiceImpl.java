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
import com.backend.todolist.service.security.SecurityService;
import com.backend.todolist.service.user.UserService;
import com.backend.todolist.utils.exception.Errors;
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
    private final SecurityService securityService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService tokenProvider, AuthMapper authMapper, AccountService accountService, UserService userService, AccountMapper accountMapper, PasswordEncoder passwordEncoder, SecurityService securityService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = tokenProvider;
        this.authMapper = authMapper;
        this.accountService = accountService;
        this.userService = userService;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
    }

    @Override
    public LoginOutputDto login(LoginInputDto loginInputDto) {
        if(accountService.isAccountExist(loginInputDto.getUsername())) {
            String encodePassword = accountService.getAccount(loginInputDto.getUsername()).getPassword();
            if(!passwordEncoder.matches(loginInputDto.getPassword(), encodePassword))
                throw Errors.USERNAME_OR_PASSWORD_INCORRECT;
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginInputDto.getUsername(),
                            loginInputDto.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtService.generateToken((UserDetailEntity) authentication.getPrincipal());
            String refreshJwt = jwtService.generateRefreshToken((UserDetailEntity) authentication.getPrincipal());
            return new LoginOutputDto(jwt, refreshJwt);
        } else {
            throw Errors.USERNAME_OR_PASSWORD_INCORRECT;
        }

    }



    @Override
    public RegisterOutputDto register(RegisterInputDto registerInputDto) {
        AccountInputDto accountInputDto = authMapper.getAccountInputDtoFromRegisterInputDto(registerInputDto);
        UserInputDto userInputDto = authMapper.getUserInputDtoFromRegisterInputDto(registerInputDto);
        UserDetailOutputDto userDetailOutputDto = userService.createUser(userInputDto);
        accountInputDto.setUserId(userDetailOutputDto.getUserId());
        accountInputDto.setPassword(passwordEncoder.encode(registerInputDto.getPassword()));
        System.out.println(accountInputDto.getPassword());
        accountService.createAccount(accountInputDto);
        accountMapper.getEntityFromInput(accountInputDto);
        return authMapper.getRegisterOutputDtoFromUserDetailOutputDto(userDetailOutputDto);
    }

    @Override
    public LoginOutputDto refreshToken(String refreshToken) {
        UserDetailEntity userDetail = (UserDetailEntity) securityService.loadUserByUsername(jwtService.getUserNameFromToken(refreshToken));
        String jwt = jwtService.generateToken(userDetail);
        String refreshJwt = jwtService.generateRefreshToken(userDetail);
        return new LoginOutputDto(jwt, refreshJwt);
    }


}
