package com.backend.todolist.service.jwt;

import com.backend.todolist.entity.UserDetailEntity;

public interface JwtService {
    String generateToken(UserDetailEntity username);
    Long getUsernameFromToken(String token);
    Boolean validateToken(String token);
}
