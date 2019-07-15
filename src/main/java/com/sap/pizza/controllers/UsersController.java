package com.sap.pizza.controllers;

import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.enums.UserRole;
import com.sap.pizza.exceptions.UserNotFoundException;
import com.sap.pizza.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin/users")
public class UsersController {

    private final UsersService service;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UsersController(UsersService service, BCryptPasswordEncoder encoder) {
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping("/")
    public String users(Principal principal, Model model) {
        model.addAttribute("title", "Users");
        model.addAttribute("subtext", "List of users.");
        model.addAttribute("users", service.getByRole(UserRole.EMPLOYEE));
        model.addAttribute("userStr", principal.getName());
        return "admin/users/list";
    }

    @GetMapping("/add")
    public String viewRegisterUser(Principal principal, Model model) {
        model.addAttribute("title", "Create user");
        model.addAttribute("subtext", "Creation of user.");
        model.addAttribute("userStr", principal.getName());
        return "admin/users/add";
    }

    @PostMapping("/add")
    public String registerUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        ApplicationUser user = new ApplicationUser(username, encoder.encode(password), UserRole.EMPLOYEE);
        service.save(user);
        return "redirect:/admin/users/";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable int id, Principal principal,  Model model) throws UserNotFoundException {
        model.addAttribute("title", "Edit user");
        model.addAttribute("subtext", "Refactor of user.");
        model.addAttribute("user", service.get(id));
        model.addAttribute("userStr", principal.getName());

        return "admin/users/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable int id, @RequestParam("username") String username, @RequestParam("password") String password) throws UserNotFoundException {
        ApplicationUser user = service.get(id);
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        service.save(user);
        return "redirect:/admin/users/";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable int id){
        service.deleteById(id);

        return "redirect:/admin/users/";
    }
}
