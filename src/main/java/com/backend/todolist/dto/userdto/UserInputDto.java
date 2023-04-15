package com.backend.todolist.dto.userdto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserInputDto {
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String fullName;

    private Date dateOfBirth;
    private String phoneNumber;
    @NotNull
    @NotEmpty
    private String email;
    private String avatar;
}
