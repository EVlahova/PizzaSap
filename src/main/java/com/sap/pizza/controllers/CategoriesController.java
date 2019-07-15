package com.sap.pizza.controllers;

import com.sap.pizza.entities.Category;
import com.sap.pizza.exceptions.CategoryNotFoundException;
import com.sap.pizza.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/categories")
public class CategoriesController {

    private final CategoriesService service;

    @Autowired
    public CategoriesController(CategoriesService service) {
        this.service = service;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String categories(Principal principal, Model model){
        model.addAttribute("title", "Categories");
        model.addAttribute("subtext", "List of categories.");
        model.addAttribute("categories", service.list());
        model.addAttribute("userStr", principal.getName());

        return "admin/categories/list";
    }

    @GetMapping( value = "/add")
    public String viewAddCategory(Principal principal, Model model){
        model.addAttribute("title", "Create Category");
        model.addAttribute("subtext", "Creation of category.");
        model.addAttribute("userStr", principal.getName());

        return "admin/categories/add";
    }

    @PostMapping(value = "/add")
    public String addCategory(@RequestParam("name") String name){
        service.save(new Category(name));

        return "redirect:/admin/categories/";
    }

    @GetMapping(value = "/{id}")
    public String deleteCategory(@PathVariable int id){
        service.deleteById(id);

        return "redirect:/admin/categories";
    }


    @GetMapping(value = "/edit/{id}")
    public String getCategory(@PathVariable int id, Principal principal, Model model) throws CategoryNotFoundException {
        model.addAttribute("title", "Edit Category");
        model.addAttribute("subtext", "Refactor of category.");
        model.addAttribute("category", service.get(id));
        model.addAttribute("userStr", principal.getName());

        return "admin/categories/edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String editCategory(@PathVariable int id, @RequestParam("name") String name) throws CategoryNotFoundException {
        Category category = service.get(id);
        category.setName(name);
        service.save(category);

        return "redirect:/admin/categories/";
    }
}

