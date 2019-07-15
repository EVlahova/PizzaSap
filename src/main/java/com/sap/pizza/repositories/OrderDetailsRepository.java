package com.sap.pizza.repositories;

import com.sap.pizza.entities.OrderDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {
    List<OrderDetails> findAll();
}
