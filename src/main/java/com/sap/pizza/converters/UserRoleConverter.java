package com.sap.pizza.converters;

import com.sap.pizza.enums.UserRole;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        return userRole.getKey();
    }

    @Override
    public UserRole convertToEntityAttribute(String key) {
        return UserRole.fromKey(key);
    }
}
