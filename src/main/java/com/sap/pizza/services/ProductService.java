package com.sap.pizza.services;

import com.sap.pizza.entities.Product;
import com.sap.pizza.exceptions.ProductNotFoundException;
import com.sap.pizza.interfaces.IDAOService;
import com.sap.pizza.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IDAOService<Product> {

    private final ProductsRepository repo;

    @Autowired
    public ProductService(ProductsRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Product> list() {
        return repo.findAll();
    }

    @Override
    public Product get(int id) throws ProductNotFoundException {
        return repo.findById(id).orElseThrow(() -> new ProductNotFoundException(String.format("Product with id: %d not found!!", id)));
    }

    @Override
    public Product save(Product product) {
        return repo.save(product);
    }

    @Override
    public void delete(Product product) {
        repo.delete(product);
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
