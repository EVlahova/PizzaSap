package com.sap.pizza.controllers;

import com.sap.pizza.dto.OrderDto;
import com.sap.pizza.entities.Order;
import com.sap.pizza.exceptions.OrderNotFoundException;
import com.sap.pizza.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3001", maxAge = 3600)
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrdersService service;

    @Autowired
    public OrderController(OrdersService service){
        this.service = service;
    }

    //запазвам в базата order-a
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Order addOrders(@Valid @RequestBody OrderDto dto){
        return service.save(new Order(dto));
    }

    //изкарвам order-те в списък
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Order> orders(){
        return service.list();
    }

    //копирам стария order(продуктите) в нов order(който съдържа старите продукти)
    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Order reOrder(@PathVariable("id") int id) throws OrderNotFoundException {
        Order order = service.get(id);
        return service.save(order);
    }

    //получавам си id-то от request-a
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Order getOrder(@PathVariable("id") int id) throws OrderNotFoundException {
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteOrder(@PathVariable("id") int id) {
        service.deleteById(id);
    }
}
