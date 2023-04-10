package com.backend.todolist.service.security;

import com.backend.todolist.entity.AccountEntity;
import com.backend.todolist.entity.UserDetailEntity;
import com.backend.todolist.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    private AccountRepository accountRepository;

    SecurityService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AccountEntity account = accountRepository.getByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailEntity(account);
    }

    public UserDetails loadUserById(Long id) {
        AccountEntity account = accountRepository.getByUserId(id);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailEntity(account);
    }
}
