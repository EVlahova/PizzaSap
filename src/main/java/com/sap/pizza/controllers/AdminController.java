package com.sap.pizza.controllers;

import com.sap.pizza.services.OrdersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final OrdersService service;

    public AdminController(OrdersService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    public String dashboard(Principal principal, Model model) {
        model.addAttribute("orders_num", service.count());
        model.addAttribute("order_sum", String.format("%d лв", service.sum()));
        model.addAttribute("userStr", principal.getName());
        return "dashboard";
    }

}
