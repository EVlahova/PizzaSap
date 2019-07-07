package com.sap.pizza.repositories;

import com.sap.pizza.entities.OrderDetails;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Integer> {

}
