package com.backend.todolist.utils.exception;


public interface Errors {
    RestException PROJECT_NOT_FOUND = new RestException("0", "PROJECT_NOT_FOUND");
    RestException IS_EXIST_BY_ID = new RestException("1", "IS_EXIST_BY_ID");
    RestException PROJECT_DEADLINE_IS_BEFORE_NOW = new RestException("2", "PROJECT_DEADLINE_IS_BEFORE_NOW");
}
