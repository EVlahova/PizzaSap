package com.sap.pizza.dto;


public class ProductDto {

    private final String name;
    private final double price;
    private final int quantity;
    private final boolean status;

    public ProductDto(String name, double price, int quantity, boolean status){
        this.price = price;
        this.name = name;
        this.quantity = quantity;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isStatus() {
        return status;
    }
}
