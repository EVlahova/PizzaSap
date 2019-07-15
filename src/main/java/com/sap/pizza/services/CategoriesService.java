package com.sap.pizza.services;

import com.sap.pizza.entities.Category;
import com.sap.pizza.entities.Product;
import com.sap.pizza.exceptions.CategoryNotFoundException;
import com.sap.pizza.exceptions.ProductNotFoundException;
import com.sap.pizza.interfaces.IDAOService;
import com.sap.pizza.repositories.CategoriesRepository;
import com.sap.pizza.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService implements IDAOService<Category> {

    private final CategoriesRepository repo;

    @Autowired
    public CategoriesService(CategoriesRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Category> list() {
        return repo.findAll();
    }

    @Override
    public Category get(int id) throws CategoryNotFoundException {
        return repo.findById(id).orElseThrow(() -> new CategoryNotFoundException(String.format("Category with id: %d not found!!", id)));
    }

    public Category getByName(String name) throws CategoryNotFoundException {
        return repo.findByName(name).orElseThrow(() -> new CategoryNotFoundException(String.format("Category with name: %s not found!!", name)));
    }

    @Override
    public Category save(Category category) {
        return repo.save(category);
    }

    @Override
    public void delete(Category category) {
        repo.delete(category);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public long count() {
        return repo.count();
    }
}
