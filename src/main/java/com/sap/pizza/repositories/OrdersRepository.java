package com.sap.pizza.repositories;

import com.sap.pizza.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Order, Integer> {

}
