package com.backend.todolist.service.user;

import com.backend.todolist.dto.userdto.UserDetailOutputDto;
import com.backend.todolist.dto.userdto.UserInputDto;
import com.backend.todolist.dto.userdto.UserOutputDto;
import com.backend.todolist.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity getUserEntityFromUserInputDto(UserInputDto userInputDto);

    UserOutputDto getUserOutputDtoFromUserEntity(UserEntity userEntity);

    UserDetailOutputDto getUserDetailOutputDtoFromUserEntity(UserEntity userEntity);
}
