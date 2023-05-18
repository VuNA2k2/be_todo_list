package com.backend.todolist.utils.exception;


public interface Errors {
    RestException PROJECT_NOT_FOUND = new RestException("0", "PROJECT_NOT_FOUND");
    RestException TASK_NOT_FOUND = new RestException("1", "TASK_NOT_FOUND");
    RestException PROJECT_DEADLINE_IS_BEFORE_NOW = new RestException("2", "PROJECT_DEADLINE_IS_BEFORE_NOW");
    RestException TASK_DEADLINE_IS_BEFORE_NOW = new RestException("3", "TASK_DEADLINE_IS_BEFORE_NOW");
    RestException PROJECT_DEADLINE_IS_BEFORE_TASK_DEADLINE = new RestException("4", "PROJECT_DEADLINE_IS_BEFORE_TASK_DEADLINE");
    RestException INVALID_TOKEN = new RestException("5", "INVALID_TOKEN");
    RestException TOKEN_EXPIRED = new RestException("6", "TOKEN_EXPIRED");
    RestException USER_NOT_FOUND = new RestException("7", "USER_NOT_FOUND");
    RestException USERID_EXISTED = new RestException("8", "USERID_EXISTED");
    RestException USERNAME_NOT_FOUND = new RestException("9", "USERNAME_NOT_FOUND");
    RestException PASSWORD_NOT_MATCH = new RestException("10", "PASSWORD_NOT_MATCH");
    RestException USERNAME_EXISTED = new RestException("11", "USERNAME_EXISTED");
    RestException UNAUTHORIZED = new RestException("12", "UNAUTHORIZED");
    RestException PROJECT_STATUS_IS_TODO = new RestException("13", "PROJECT_STATUS_IS_TODO");
    RestException PROJECT_STATUS_IS_DONE = new RestException("14", "PROJECT_STATUS_IS_DONE");
    RestException NOTE_NOT_FOUND = new RestException("15", "NOTE_NOT_FOUND");
    RestException TASK_STATUS_IS_DONE = new RestException("16", "TASK_STATUS_IS_DONE");
}
