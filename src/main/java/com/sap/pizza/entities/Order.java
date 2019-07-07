package com.sap.pizza.entities;

import com.sap.pizza.dto.OrderDto;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11, nullable = false)
    private final int id;

    @Column(name = "total",nullable = false)
    private final double total;

   @OneToMany(fetch = FetchType.LAZY)
   @JoinColumn(name = "detail_id",nullable = false)
   private final Set<OrderDetails> orderDetails;

   public Order(){
       //for JPA
       this(0,0.0,null);
   }

   public Order(OrderDto dto){
       this(0, dto.getTotal(), dto.getOrderDetails());
   }

   public Order(int id, double total, Set<OrderDetails> orderDetails){
       this.id = id;
       this.total = total;
       this.orderDetails = orderDetails;
   }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return total;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }
}
