package com.sap.pizza.repositories;

import com.sap.pizza.entities.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CategoriesRepository extends CrudRepository<Category, Integer> {
   List<Category> findAll();
   Optional<Category> findByName(String name);
}
