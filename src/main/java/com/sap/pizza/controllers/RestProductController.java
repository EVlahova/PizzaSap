package com.sap.pizza.controllers;

import com.sap.pizza.entities.Product;
import com.sap.pizza.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/products")
public class RestProductController {

    private final ProductService service;

    @Autowired
    public RestProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, List<Product>> products() {
        Map<String, List<Product>> result = new HashMap<>();
        result.put("products", service.list());
        return result;
    }

}
