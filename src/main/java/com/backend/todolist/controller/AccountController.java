package com.backend.todolist.controller;

import com.backend.todolist.entity.AccountEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @GetMapping("/test")
    public AccountEntity test() {
        AccountEntity account = new AccountEntity();
        account.setUserName("test");
        account.setPassword("test");
        return account;
    }
}
