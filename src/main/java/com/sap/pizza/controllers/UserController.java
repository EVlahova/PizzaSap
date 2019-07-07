package com.sap.pizza.controllers;

import com.sap.pizza.dto.UserDto;
import com.sap.pizza.entities.User;
import com.sap.pizza.exceptions.UserNotFoundException;
import com.sap.pizza.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersService service;

    @Autowired
    public UserController(UsersService service) {
        this.service = service;
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User login(@Valid @RequestBody UserDto userDto) throws UserNotFoundException {
        return service.findByUsernameAndPassword(userDto.getUsername(),userDto.getPassword());
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public User addUsers(@Valid @RequestBody UserDto userDto){
       return service.save(new User(0,userDto.getUsername(),userDto.getPassword(),userDto.getRole()));
    }



}
