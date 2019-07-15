package com.sap.pizza.exceptions;

public class CategoryNotFoundException extends EntityNotFoundException {

    public CategoryNotFoundException(String msg) {
        super(msg);
    }

}
