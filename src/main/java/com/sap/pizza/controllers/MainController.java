package com.sap.pizza.controllers;

import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.services.CategoriesService;
import com.sap.pizza.services.ProductService;
import com.sap.pizza.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    private final ProductService productService;
    private final UsersService userService;
    private final CategoriesService categoriesService;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public MainController(UsersService usersService, BCryptPasswordEncoder encoder,
                          ProductService productService, CategoriesService categoriesService) {
        this.userService = usersService;
        this.productService = productService;
        this.categoriesService = categoriesService;
        this.encoder = encoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
}
