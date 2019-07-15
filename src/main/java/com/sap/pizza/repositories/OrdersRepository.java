package com.sap.pizza.repositories;

import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.entities.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();
    List<Order> findAllByUser(ApplicationUser user);

    @Query(value = "SELECT SUM(times_ordered) FROM orders", nativeQuery = true)
    long getNumberOfOrders();

    @Query(value = "SELECT SUM(times_ordered * total) FROM orders", nativeQuery = true)
    long getSumOfOrders();

}
