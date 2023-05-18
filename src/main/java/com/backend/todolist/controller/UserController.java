package com.backend.todolist.controller;

import com.backend.todolist.dto.userdto.UserDetailOutputDto;
import com.backend.todolist.dto.userdto.UserInputDto;
import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.response.Response;
import com.backend.todolist.service.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    final private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/detail")
    public Response<UserDetailOutputDto> getUserDetail() {
        return Response.success(userService.getUserDetail(getUserDetailEntity().getAccount().getUserId()));
    }

    @PutMapping
    public Response<UserDetailOutputDto> updateUserDetail(@RequestBody UserInputDto userInputDto) {
        return Response.success(userService.updateUser(getUserDetailEntity().getAccount().getUserId(), userInputDto));
    }

    private UserDetailEntity getUserDetailEntity() {
        return (UserDetailEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
