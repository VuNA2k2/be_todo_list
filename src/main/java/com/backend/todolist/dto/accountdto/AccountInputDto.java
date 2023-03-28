package com.backend.todolist.dto.accountdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountInputDto {
    private String userName;
    private String password;

    public AccountInputDto() {

    }

    public AccountInputDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
