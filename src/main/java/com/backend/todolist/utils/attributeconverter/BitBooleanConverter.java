package com.backend.todolist.utils.attributeconverter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Optional;


@Converter(autoApply = true)
public class BitBooleanConverter implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        return Optional.ofNullable(aBoolean)
                .map(b -> b ? 1 : 0)
                .orElse(null);
    }

    @Override
    public Boolean convertToEntityAttribute(Integer integer) {
        return Optional.ofNullable(integer)
                .map(i -> i == 1)
                .orElse(null);
    }
}
