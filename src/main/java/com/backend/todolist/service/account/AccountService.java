package com.backend.todolist.service.account;

import com.backend.todolist.dto.accountdto.AccountInputDto;
import com.backend.todolist.dto.accountdto.AccountOutputDto;
import com.backend.todolist.entity.AccountEntity;


public interface AccountService {
    AccountEntity createAccount(AccountInputDto inputDto);

    void deleteAccount(Long userId);

    AccountEntity updateAccount(Long userId, AccountInputDto inputDto);

    AccountEntity changePassword(Long userId, String newPassword);

    AccountEntity getAccount(Long userId);

    AccountEntity getAccount(String userName);

    AccountOutputDto getOutputFromEntity(AccountEntity entity);

    AccountEntity getEntityFromInput(AccountInputDto inputDto);

    boolean isAccountExist(String userName);

    boolean existAccount(String userName, String password);
}
