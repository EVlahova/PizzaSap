package com.sap.pizza.controllers;

import com.sap.pizza.dto.UserDto;
import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.entities.Order;
import com.sap.pizza.enums.UserRole;
import com.sap.pizza.exceptions.UserNotFoundException;
import com.sap.pizza.services.OrdersService;
import com.sap.pizza.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class RestUserController {

    private final UsersService service;
    private final OrdersService ordersService;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public RestUserController(UsersService service, OrdersService ordersService, BCryptPasswordEncoder encoder){
        this.service = service;
        this.ordersService = ordersService;
        this.encoder = encoder;
    }

    //Log in, метода връща стойност т.е. consumes и produces
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApplicationUser login(@Valid @RequestBody UserDto userDto) throws UserNotFoundException {
        ApplicationUser user = service.findByUsername(userDto.getUsername());
        if(encoder.matches(userDto.getPassword(), user.getPassword())) {
            return user;
        }

        return null;
    }

    //Register , void method т.е. само consumes
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApplicationUser addUsers(@Valid @RequestBody UserDto userDto){
        return service.save(new ApplicationUser(userDto.getUsername(), encoder.encode(userDto.getPassword()), UserRole.USER));
    }

    //Приермам request-a от фронтенд аппито
    @GetMapping(value = "/{user_id}/orders/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, List<Order>> ordersForCurrentUser(@PathVariable("user_id") int id) throws UserNotFoundException {
        ApplicationUser user = service.get(id);
        Map<String, List<Order>> result = new HashMap<>();
        result.put("orders", ordersService.userOrders(user));
        return result;
    }



}
