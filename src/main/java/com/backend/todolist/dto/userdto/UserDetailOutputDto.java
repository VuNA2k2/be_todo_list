package com.backend.todolist.dto.userdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserDetailOutputDto {
    private String userId;
    private String userName;
    private String fullName;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String avatar;
}
