package com.backend.todolist.repository;

import com.backend.todolist.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getByUserId(Long userId);

    UserEntity save(UserEntity userEntity);

    void deleteByUserId(Long userId);

    UserEntity getByUsername(String userName);

    boolean existsUserEntitiesByUserId(Long userId);

    boolean existsUserEntitiesByUsername(String userName);

    boolean existsUserEntitiesByUsernameAndUserId(String userName, Long userId);
}
