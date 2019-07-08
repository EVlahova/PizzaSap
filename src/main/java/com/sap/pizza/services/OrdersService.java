package com.sap.pizza.services;

import com.sap.pizza.entities.Order;
import com.sap.pizza.exceptions.OrderNotFoundException;
import com.sap.pizza.interfaces.IDAOService;
import com.sap.pizza.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService implements IDAOService<Order> {

    private final OrdersRepository repo;

    @Autowired
    public OrdersService(OrdersRepository repo){
        this.repo = repo;
    }

    @Override
    public List<Order> list() {
        return repo.findAll();
    }

    @Override
    public Order get(int id) throws OrderNotFoundException {
        return repo.findById(id).orElseThrow(() -> new OrderNotFoundException(String.format("Order with id: %d not found!!",id)));
    }

    @Override
    public Order save(Order order) {
        return repo.save(order);
    }

    @Override
    public void delete(Order order) {
        repo.delete(order);
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
