package com.sap.pizza.exceptions;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
