package com.backend.todolist.dto.logindto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginOutputDto {
    private String accessToken;
    private String refreshToken;
}
