package com.backend.todolist.dto.userdto;

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
    private String userName;
    private String fullName;

    private Date dateOfBirth;
    private String phoneNumber;
    private String email;
    private String avatar;
}
