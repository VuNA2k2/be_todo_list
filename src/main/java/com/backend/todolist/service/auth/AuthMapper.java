package com.backend.todolist.service.auth;

import com.backend.todolist.dto.accountdto.AccountInputDto;
import com.backend.todolist.dto.registerdto.RegisterInputDto;
import com.backend.todolist.dto.registerdto.RegisterOutputDto;
import com.backend.todolist.dto.userdto.UserDetailOutputDto;
import com.backend.todolist.dto.userdto.UserInputDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AccountInputDto getAccountInputDtoFromRegisterInputDto(RegisterInputDto registerInputDto);
    UserInputDto getUserInputDtoFromRegisterInputDto(RegisterInputDto registerInputDto);
    RegisterOutputDto getRegisterOutputDtoFromUserDetailOutputDto(UserDetailOutputDto userOutputDto);
}
