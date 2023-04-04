package com.backend.todolist.utils.attributeconverter;

import com.backend.todolist.entity.Priority;
import jakarta.persistence.AttributeConverter;

public class PriorityConverter implements AttributeConverter<Priority, String> {
    @Override
    public String convertToDatabaseColumn(Priority priority) {
        return priority.name();
    }

    @Override
    public Priority convertToEntityAttribute(String s) {
        return Priority.valueOf(s);
    }
}
