package com.backend.todolist.service.account;

import com.backend.todolist.dto.accountdto.AccountInputDto;
import com.backend.todolist.dto.accountdto.AccountOutputDto;
import com.backend.todolist.entity.AccountEntity;
import com.backend.todolist.repository.AccountRepository;
import com.backend.todolist.utils.exception.Errors;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    private final AccountRepository accountRepository;
    public AccountServiceImpl(AccountMapper accountMapper, AccountRepository accountRepository) {
        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountEntity createAccount(AccountInputDto inputDto) {
        if(accountRepository.existsAccountEntitiesByUsername(inputDto.getUsername()))
            throw Errors.USERNAME_EXISTED;
        if(accountRepository.existsAccountEntitiesByUserId(inputDto.getUserId()))
            throw Errors.USERID_EXISTED;
        return accountRepository.save(accountMapper.getEntityFromInput(inputDto));
    }

    @Override
    public void deleteAccount(Long userId) {
        accountRepository.deleteById(userId);
    }

    @Override
    public AccountEntity updateAccount(Long userId, AccountInputDto inputDto) {
        AccountEntity accountEntity = accountRepository.getByUserId(userId);
        accountEntity.setUsername(inputDto.getUsername());
        accountEntity.setPassword(inputDto.getPassword());
        return accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity changePassword(Long userId, String newPassword) {
        AccountEntity accountEntity = accountRepository.getByUserId(userId);
        accountEntity.setPassword(newPassword);
        return accountRepository.save(accountEntity);
    }

    @Override
    public AccountEntity getAccount(Long userId) {
        return accountRepository.getByUserId(userId);
    }

    @Override
    public AccountEntity getAccount(String userName) {
        return accountRepository.getByUsername(userName);
    }

    @Override
    public AccountOutputDto getOutputFromEntity(AccountEntity entity) {
        return accountMapper.getOutputFromEntity(entity);
    }

    @Override
    public AccountEntity getEntityFromInput(AccountInputDto inputDto) {
        return accountMapper.getEntityFromInput(inputDto);
    }
}
