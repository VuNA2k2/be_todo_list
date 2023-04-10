package com.backend.todolist.repository;

import com.backend.todolist.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    AccountEntity getByUserId(Long userId);
    AccountEntity getByUsername(String username);

    AccountEntity save(AccountEntity accountEntity);

    boolean existsAccountEntitiesByUserId(Long userId);

    boolean existsAccountEntitiesByUsername(String username);

    boolean existsAccountEntitiesByUsernameAndUserId(String userName, Long userId);
}
