package com.backend.todolist.service.user;

import com.backend.todolist.dto.userdto.UserDetailOutputDto;
import com.backend.todolist.dto.userdto.UserInputDto;
import com.backend.todolist.dto.userdto.UserOutputDto;
import com.backend.todolist.entity.UserEntity;
import com.backend.todolist.repository.UserRepository;

public class UserServiceImpl implements UserService {

    UserMapper userMapper;
    UserRepository userRepository;
    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailOutputDto createUser(UserInputDto userInputDto) {
        if(userRepository.existsUserEntitiesByUserName(userInputDto.getUserName())) {
            throw new RuntimeException("User name is already existed");
        }
        UserEntity userEntity = userMapper.getUserEntityFromUserInputDto(userInputDto);
        return userMapper.getUserDetailOutputDtoFromUserEntity(userRepository.save(userEntity));
    }

    @Override
    public UserOutputDto getUserByUserName(Long userId) {
        return userMapper.getUserOutputDtoFromUserEntity(userRepository.getByUserId(userId));
    }

    @Override
    public UserDetailOutputDto getUserDetail(Long userId) {
        return userMapper.getUserDetailOutputDtoFromUserEntity(userRepository.getByUserId(userId));
    }

    @Override
    public UserDetailOutputDto updateUser(Long userId, UserInputDto userInputDto) {
        UserEntity userEntity = userMapper.getUserEntityFromUserInputDto(userInputDto);
        userEntity.setUserId(userId);
        return userMapper.getUserDetailOutputDtoFromUserEntity(userRepository.save(userEntity));
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserOutputDto getUserByUserName(String userName) {
        return null;
    }
}
