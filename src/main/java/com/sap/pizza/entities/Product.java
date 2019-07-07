package com.sap.pizza.entities;

import com.sap.pizza.dto.ProductDto;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11, nullable = false)
    private final int id;

    @Column(name = "name", nullable = false)
    private final String name;

    @Column(name = "price", nullable = false)
    private final double price;

    @Column(name = "quantity", nullable = false)
    private final int quantity;

    @Column(name = "product_status", nullable = false)
    private final boolean status;

    public Product() {
        this(0,null,0.0,0,false);
    }

    public Product(ProductDto dto) {
        this(0, dto.getName(),dto.getPrice(),dto.getQuantity(),dto.isStatus());
    }

    public Product(int id, String name, double price, int quantity, boolean status) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
        this.status = status;
    }

    public int getId() {
        return id;
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

    public boolean isStatus(){
      return status;
    }
}
