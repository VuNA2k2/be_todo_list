package com.backend.todolist.dto.logindto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshInputDto {
    @NotNull
    private String refreshToken;
}
