package com.backend.todolist.repository;

import com.backend.todolist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getByUserId(Long userId);

    UserEntity save(UserEntity userEntity);

    void deleteByUserId(Long userId);

    UserEntity getByUserName(String userName);

    boolean existsUserEntitiesByUserId(Long userId);

    boolean existsUserEntitiesByUserName(String userName);

    boolean existsUserEntitiesByUserNameAndUserId(String userName, Long userId);
}
