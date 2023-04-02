package com.backend.todolist.service.authen;

public interface AuthenService {
    String login(String userName, String password);
    String register(String userName, String password, String confirmPassword);
    String logout(String token);


}
