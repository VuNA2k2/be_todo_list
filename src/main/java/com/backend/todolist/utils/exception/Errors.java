package com.backend.todolist.utils.exception;


public interface Errors {
    RestException PROJECT_NOT_FOUND = new RestException("0", "PROJECT_NOT_FOUND");
    RestException TASK_NOT_FOUND = new RestException("1", "TASK_NOT_FOUND");
    RestException PROJECT_DEADLINE_IS_BEFORE_NOW = new RestException("2", "PROJECT_DEADLINE_IS_BEFORE_NOW");
    RestException TASK_DEADLINE_IS_BEFORE_NOW = new RestException("3", "TASK_DEADLINE_IS_BEFORE_NOW");
    RestException PROJECT_DEADLINE_IS_BEFORE_TASK_DEADLINE = new RestException("4", "PROJECT_DEADLINE_IS_BEFORE_TASK_DEADLINE");
    RestException INVALID_TOKEN = new RestException("5", "INVALID_TOKEN");
    RestException TOKEN_EXPIRED = new RestException("6", "TOKEN_EXPIRED");
}
