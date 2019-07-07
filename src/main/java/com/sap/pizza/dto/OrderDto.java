package com.sap.pizza.dto;

import com.sap.pizza.entities.OrderDetails;

import java.util.Set;

public class OrderDto {

    private final double total;
    private final Set<OrderDetails> orderDetails;

    public OrderDto(double total, Set<OrderDetails> orderDetails){
        this.total = total;
        this.orderDetails = orderDetails;
    }

    public double getTotal() {
        return total;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }
}
