package com.sap.pizza.controllers;

import com.sap.pizza.dto.ProductDto;
import com.sap.pizza.entities.Product;
import com.sap.pizza.exceptions.CategoryNotFoundException;
import com.sap.pizza.exceptions.ProductNotFoundException;
import com.sap.pizza.services.CategoriesService;
import com.sap.pizza.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin/products")
public class ProductsController {

    private final ProductService service;
    private final CategoriesService categoryService;

    @Autowired
    public ProductsController(ProductService service, CategoriesService categoriesService) {
        this.service = service;
        this.categoryService = categoriesService;
    }

    @GetMapping(value = "/")
    public String products(Principal principal, Model model){
        model.addAttribute("title", "Products");
        model.addAttribute("subtext", "List of products.");
        model.addAttribute("products", service.list());
        model.addAttribute("userStr", principal.getName());

        return "admin/products/list";
    }

    @GetMapping("/add")
    public String viewAddProduct(Principal principal, Model model) {
        model.addAttribute("title", "Create product");
        model.addAttribute("subtext", "Creation of products.");
        model.addAttribute("userStr", principal.getName());

        return "admin/products/add";
    }


    @PostMapping(value = "/add")
    public String addProducts(@RequestParam("name") String name,
                              @RequestParam("quantity") int quantity,
                              @RequestParam("price") double price,
                              @RequestParam("status") boolean status,
                              @RequestParam("category") String category) throws CategoryNotFoundException {

        ProductDto dto = new ProductDto(name, price, quantity, status, categoryService.getByName(category));
        service.save(new Product(dto));
        return "redirect:/admin/products/";
    }

    @GetMapping(value = "/{id}")
    public String deleteProduct(@PathVariable int id){
        service.deleteById(id);
        return "redirect:/admin/products/";
    }

    @GetMapping(value = "/edit/{id}")
    public String getProduct(@PathVariable int id, Principal principal, Model model) throws ProductNotFoundException {
        model.addAttribute("title", "Edit Product");
        model.addAttribute("subtext", "Refactor of product.");
        model.addAttribute("product", service.get(id));
        model.addAttribute("userStr", principal.getName());

        return "admin/products/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, @RequestParam("name") String name,
                              @RequestParam("price") double price,
                              @RequestParam("quantity") int quantity,
                              @RequestParam("status") boolean status,
                              @RequestParam("category") String category) throws ProductNotFoundException, CategoryNotFoundException {
        Product product = service.get(id);
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setStatus(status);
        product.setCategory(categoryService.getByName(category));

        service.save(product);

        return "redirect:/admin/products/";
    }
}

