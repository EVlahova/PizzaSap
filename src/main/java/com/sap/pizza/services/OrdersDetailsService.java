package com.sap.pizza.services;

import com.sap.pizza.entities.Order;
import com.sap.pizza.entities.OrderDetails;
import com.sap.pizza.exceptions.OrderNotFoundException;
import com.sap.pizza.interfaces.IDAOService;
import com.sap.pizza.repositories.OrderDetailsRepository;
import com.sap.pizza.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersDetailsService implements IDAOService<OrderDetails> {

    private final OrderDetailsRepository repo;

    @Autowired
    public OrdersDetailsService(OrderDetailsRepository repo){
        this.repo = repo;
    }

    @Override
    public List<OrderDetails> list() {
        return repo.findAll();
    }

    @Override
    public OrderDetails get(int id) throws OrderNotFoundException {
        return repo.findById(id).orElseThrow(() -> new OrderNotFoundException(String.format("Order with id: %d not found!!",id)));
    }

    @Override
    public OrderDetails save(OrderDetails order) {
        return repo.save(order);
    }

    @Override
    public void delete(OrderDetails order) {
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
