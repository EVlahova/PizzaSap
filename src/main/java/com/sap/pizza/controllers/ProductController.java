package com.sap.pizza.controllers;

import com.sap.pizza.dto.ProductDto;
import com.sap.pizza.entities.Product;
import com.sap.pizza.exceptions.ProductNotFoundException;
import com.sap.pizza.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Product> products(){
        return service.list();
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product addProducts(@Valid @RequestBody ProductDto dto){
        return service.save(new Product(dto));
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteProduct(@PathVariable int id){
        service.deleteById(id);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product getProduct(@PathVariable int id) throws ProductNotFoundException {
        return service.get(id);
    }
}
