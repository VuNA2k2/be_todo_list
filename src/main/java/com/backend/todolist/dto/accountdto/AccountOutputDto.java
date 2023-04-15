package com.backend.todolist.dto.accountdto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOutputDto {
    private Long userId;
    private String username;
    private String password;
}
