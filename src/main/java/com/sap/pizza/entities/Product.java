package com.sap.pizza.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.pizza.dto.ProductDto;

import javax.persistence.*;

@Entity
@Table(name = "products")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11, nullable = false)
    private final int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "product_status", nullable = false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product() {
        this(0,null,0.0,0,false, null);
    }

    public Product(ProductDto dto) {
        this(0, dto.getName(),dto.getPrice(),dto.getQuantity(),dto.isStatus(), dto.getCategory());
    }

    /*public Product(String name, double price, int quantity, boolean status, Category category) {
        this(0, name, price, quantity, status, category);
    }*/

    public Product(int id, String name, double price, int quantity, boolean status, Category category) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.quantity = quantity;
        this.status = status;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
