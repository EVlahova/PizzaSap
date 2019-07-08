package com.sap.pizza.repositories;

import com.sap.pizza.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Order, Integer> {
    List<Order> findAll();

}
