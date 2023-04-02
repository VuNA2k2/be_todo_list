package com.backend.todolist.service.user;

import com.backend.todolist.dto.userdto.UserDetailOutputDto;
import com.backend.todolist.dto.userdto.UserInputDto;
import com.backend.todolist.dto.userdto.UserOutputDto;

public interface UserService {
    UserDetailOutputDto createUser(UserInputDto userInputDto);

    UserOutputDto getUserByUserName(Long userId);

    UserDetailOutputDto getUserDetail(Long userId);

    UserDetailOutputDto updateUser(Long userId, UserInputDto userInputDto);

    void deleteUser(Long userId);

    UserOutputDto getUserByUserName(String userName);
}
