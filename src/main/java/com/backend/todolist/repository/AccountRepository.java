package com.backend.todolist.repository;

import com.backend.todolist.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity getByUserId(Long userId);
    AccountEntity getByUserName(String userName);

    AccountEntity save(AccountEntity accountEntity);

    boolean existsAccountEntitiesByUserId(Long userId);

    boolean existsAccountEntitiesByUserName(String userName);

    boolean existsAccountEntitiesByUserNameAndUserId(String userName, Long userId);
}
