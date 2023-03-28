package com.backend.todolist.dto.accountdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountOutputDto {
    private Long userId;
    private String userName;
    private String password;

    public AccountOutputDto() {
    }

    public AccountOutputDto(Long userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}
