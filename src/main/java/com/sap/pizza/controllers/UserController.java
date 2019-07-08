package com.sap.pizza.controllers;

import com.sap.pizza.dto.UserDto;
import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.exceptions.UserNotFoundException;
import com.sap.pizza.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService service;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UsersService service, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.service = service;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApplicationUser addUsers(@Valid @RequestBody UserDto userDto){
        ApplicationUser user = new ApplicationUser(0,userDto.getUsername(), passwordEncoder.encode(userDto.getPassword()),userDto.getRole());
        return service.save(user);
    }



}
