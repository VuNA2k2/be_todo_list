package com.backend.todolist.dto.registerdto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RegisterInputDto {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private Date dateOfBirth;
    private String phoneNumber;
    private String avatar;
}
