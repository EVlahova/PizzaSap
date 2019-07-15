package com.sap.pizza.enums;

public enum UserRole {
    USER("user"),
    EMPLOYEE("employee");

    private final String key;

    UserRole(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static UserRole fromKey(String key) {
        switch (key) {
            case "user":
                return UserRole.USER;

            case "employee":
                return UserRole.EMPLOYEE;

            default:
                throw new IllegalArgumentException("Key [" + key + "] not supported.");
        }
    }
}
