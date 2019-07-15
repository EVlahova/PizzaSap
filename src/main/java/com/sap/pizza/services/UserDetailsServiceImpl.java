package com.sap.pizza.services;

import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersService service;

    @Autowired
    public UserDetailsServiceImpl(UsersService service) {
        this.service = service;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = null;
        try {
            applicationUser = service.findByUsername(username);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));

        return new org.springframework.security.core.userdetails.User(applicationUser.getUsername(), applicationUser.getPassword(), grantedAuthorities);
    }
}