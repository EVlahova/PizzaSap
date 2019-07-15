package com.sap.pizza.dto;

import com.sap.pizza.enums.UserRole;

public class UserDto {

    private final String username;
    private final String password;
    private final UserRole role;

    public UserDto() {
        this(null, null, null);
    }

    public UserDto(String username, String password, UserRole role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole(){
        return role;
    }
}
