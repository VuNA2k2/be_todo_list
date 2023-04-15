package com.backend.todolist.dto.accountdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AccountInputDto {
    @NotNull
    private String username;
    @NotNull
    @Min(8)
    private String password;
    @NotNull
    private Long userId;
}
