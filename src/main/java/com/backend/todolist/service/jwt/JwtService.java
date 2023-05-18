package com.backend.todolist.service.jwt;

import com.backend.todolist.entity.UserDetailEntity;

public interface JwtService {
    String generateToken(UserDetailEntity userDetail);
    Long getUserIdFromToken(String token);
    String getUserNameFromToken(String token);
    String generateRefreshToken(UserDetailEntity userDetail);
    Boolean validateToken(String token);
}
