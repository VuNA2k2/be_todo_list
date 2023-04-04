package com.backend.todolist.utils.attributeconverter;

import com.backend.todolist.entity.NotificationType;
import jakarta.persistence.AttributeConverter;

public class NotificationTypeConverter implements AttributeConverter<NotificationType, String> {
    @Override
    public String convertToDatabaseColumn(NotificationType notificationType) {
        return notificationType.name();
    }

    @Override
    public NotificationType convertToEntityAttribute(String s) {
        return NotificationType.valueOf(s);
    }
}
