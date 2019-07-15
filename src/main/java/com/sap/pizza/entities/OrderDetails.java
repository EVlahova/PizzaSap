package com.sap.pizza.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final int id;

    @ManyToOne( cascade = {CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private final Product product;

    @Column(name = "quantity")
    private int quantity;

    public OrderDetails(){
        this(0,null,0);
    }

    public OrderDetails(Product product, int quantity) {
        this(0, product, quantity);
    }

    public OrderDetails(int id, Product product, int quantity){
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
