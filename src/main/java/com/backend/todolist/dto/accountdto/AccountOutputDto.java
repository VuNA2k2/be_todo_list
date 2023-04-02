package com.backend.todolist.dto.accountdto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOutputDto {
    private Long userId;
    private String userName;
    private String password;
}