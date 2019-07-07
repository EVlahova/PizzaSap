package com.sap.pizza.exceptions;

public class ProductNotFoundException extends EntityNotFoundException {

    public ProductNotFoundException(String msg) {
        super(msg);
    }

}
