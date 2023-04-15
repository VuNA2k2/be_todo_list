package com.backend.todolist.dto.logindto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginInputDto {
//    @NotNull
//    @NotEmpty
    private String username;
//    @NotNull
//    @NotEmpty
    private String password;
}
