package com.sap.pizza.controllers;

import com.sap.pizza.entities.Order;
import com.sap.pizza.enums.OrderStatus;
import com.sap.pizza.exceptions.OrderNotFoundException;
import com.sap.pizza.services.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/orders")
public class OrdersController {

    private final OrdersService service;

    public OrdersController(OrdersService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String orders(Principal principal,  Model model) {
        model.addAttribute("title", "Orders");
        model.addAttribute("subtext", "List of orders.");
        model.addAttribute("orders", service.list());
        model.addAttribute("userStr", principal.getName());
        return "admin/orders/list";
    }


    @GetMapping("/edit/{id}")
    public String editOrder(Principal principal, @PathVariable("id") int id, Model model) throws OrderNotFoundException {
        model.addAttribute("title", "Edit order");
        model.addAttribute("subtext", "Refactoring of order.");
        model.addAttribute("order", service.get(id));
        model.addAttribute("userStr", principal.getName());

        return "admin/orders/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateOrder(@PathVariable("id") int id, @RequestParam("status") String status) throws OrderNotFoundException {
        Order order = service.get(id);
        order.setStatus(OrderStatus.fromKey(status));
        service.save(order);

        return "redirect:/admin/orders/";
    }

}
