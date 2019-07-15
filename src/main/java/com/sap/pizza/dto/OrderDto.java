package com.sap.pizza.dto;

import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.entities.OrderDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class OrderDto {

    @NotNull
    private final double total;

    @NotNull
    @NotEmpty
    private final Set<OrderDetails> orderDetails;

    @NotNull
    private final ApplicationUser user;

    public OrderDto(double total, Set<OrderDetails> orderDetails, ApplicationUser user){
        this.total = total;
        this.orderDetails = orderDetails;
        this.user = user;
    }

    public double getTotal() {
        return total;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public ApplicationUser getUser() {
        return user;
    }
}
