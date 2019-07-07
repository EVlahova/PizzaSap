package com.sap.pizza.repositories;

import com.sap.pizza.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductsRepository extends CrudRepository<Product, Integer> {
   List<Product> findAll();

}
