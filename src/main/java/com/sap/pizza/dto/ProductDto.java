package com.sap.pizza.dto;


import com.sap.pizza.entities.Category;

public class ProductDto {

    private final String name;
    private final double price;
    private final int quantity;
    private final boolean status;
    private Category category;

    public ProductDto(String name, double price, int quantity, boolean status, Category category){
        this.price = price;
        this.name = name;
        this.quantity = quantity;
        this.status = status;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }
}
