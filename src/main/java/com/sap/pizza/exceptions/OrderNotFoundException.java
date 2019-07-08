package com.sap.pizza.exceptions;

public class OrderNotFoundException extends EntityNotFoundException {
    public OrderNotFoundException(String msg){
        super(msg);
    }
}
