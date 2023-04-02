package com.backend.todolist.controller;

import com.backend.todolist.dto.accountdto.AccountInputDto;
import com.backend.todolist.dto.accountdto.AccountOutputDto;
import com.backend.todolist.response.Response;
import com.backend.todolist.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountOutputDto createAccount(@RequestBody AccountInputDto inputDto) {
        return accountService.getOutputFromEntity(accountService.createAccount(inputDto));
    }

    @GetMapping
    public AccountOutputDto getAccount(@RequestParam Long userId) {
        return accountService.getOutputFromEntity(accountService.getAccount(userId));
    }

    @PutMapping
    public AccountOutputDto updateAccount(@RequestParam Long userId, @RequestBody AccountInputDto inputDto) {
        return accountService.getOutputFromEntity(accountService.updateAccount(userId, inputDto));
    }

    @DeleteMapping
    public Response deleteAccount(@RequestParam Long userId) {
        accountService.deleteAccount(userId);
        return new Response("success", "Delete account successfully");
    }
}
