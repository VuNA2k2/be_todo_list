package com.backend.todolist.utils.attributeconverter;

import com.backend.todolist.entity.Status;
import jakarta.persistence.AttributeConverter;

public class StatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if(status == null) {
            return Status.TODO.name();
        }
        return status.name();
    }

    @Override
    public Status convertToEntityAttribute(String s) {
        if(s == null) {
            return Status.TODO;
        }
        return Status.valueOf(s);
    }
}
