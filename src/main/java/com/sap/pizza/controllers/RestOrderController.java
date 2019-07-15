package com.sap.pizza.controllers;

import com.sap.pizza.dto.OrderDto;
import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.entities.Order;
import com.sap.pizza.entities.OrderDetails;
import com.sap.pizza.entities.Product;
import com.sap.pizza.enums.OrderStatus;
import com.sap.pizza.exceptions.OrderNotFoundException;
import com.sap.pizza.exceptions.ProductNotFoundException;
import com.sap.pizza.services.OrdersService;
import com.sap.pizza.services.ProductService;
import com.sap.pizza.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
public class RestOrderController {

   private final OrdersService ordersService;
    private final ProductService productsService;

    @Autowired
    public RestOrderController(OrdersService ordersService, ProductService productsService){
       this.ordersService = ordersService;
       this.productsService = productsService;
   }

   @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createOrders(@Valid @RequestBody OrderDto dto) throws ProductNotFoundException {
        checkProductsQuantity(dto.getOrderDetails());
        ordersService.save(new Order(dto.getTotal(), dto.getOrderDetails(),dto.getUser(), OrderStatus.CREATED, 1));
        for (OrderDetails element: dto.getOrderDetails()) {
            reduceProductQuantity(element);
        }
   }

   @PostMapping(value = "/reorder", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
   public void reorder(@RequestBody Map<String, Object> payload) throws OrderNotFoundException, ProductNotFoundException {
        int order_id = (int) payload.get("order_id");
        Order order = ordersService.get(order_id);
        order.setStatus(OrderStatus.CREATED);
        order.setTimesOrdered(order.getTimesOrdered() + 1);
        for (OrderDetails element: order.getOrderDetails()) {
            reduceProductQuantity(element);
        }
   }

    private void reduceProductQuantity(OrderDetails element) throws ProductNotFoundException {
        Product product = productsService.get(element.getProduct().getId());
        int quantity = product.getQuantity() - element.getQuantity();
        if( quantity <= 0 ) {
            product.setQuantity(0);
            product.setStatus(false);
        } else {
            product.setQuantity(quantity);
        }

        productsService.save(product);
    }

    private void checkProductsQuantity(Set<OrderDetails> orderDetails) throws ProductNotFoundException {
        for (OrderDetails element: orderDetails) {
            Product product = productsService.get(element.getProduct().getId());
            if(element.getQuantity() > product.getQuantity()) {
                throw new ProductNotFoundException("Not enough product quantity available!!!");
            }

        }
    }


}
