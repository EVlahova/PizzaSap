package com.sap.pizza.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sap.pizza.converters.OrderStatusConverter;
import com.sap.pizza.enums.OrderStatus;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11, nullable = false)
    private final int id;

    @Column(name = "total",nullable = false)
    private double total;

   @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
   @JoinColumn(name = "order_id", nullable = false)
   private Set<OrderDetails> orderDetails;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private ApplicationUser user;

    @Column(name = "order_status", length = 32, columnDefinition = "varchar(32) default 'CREATED'", nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Convert(converter = OrderStatusConverter.class)
    private OrderStatus status;

    @Column(name = "times_ordered", length = 11, columnDefinition = "int(11) default 1", nullable = false)
    private int timesOrdered;

    public Order(){
       //for JPA
       this(0,0,null,null, null, 0);
   }

    public Order(double total, Set<OrderDetails> orderDetails, ApplicationUser user, OrderStatus status, int timesOrdered){
        this(0, total, orderDetails, user, status, timesOrdered);
    }

   public Order(int id, double total, Set<OrderDetails> orderDetails, ApplicationUser user, OrderStatus status, int timesOrdered){
       this.id = id;
       this.total = total;
       this.orderDetails = orderDetails;
       this.user = user;
       this.status = status;
       this.timesOrdered = timesOrdered;
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

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ApplicationUser getUser() {
        return user;
    }

    public void setUser(ApplicationUser user) {
        this.user = user;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getTimesOrdered() {
        return timesOrdered;
    }

    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }
}
