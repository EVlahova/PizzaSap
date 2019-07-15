package com.sap.pizza.repositories;

import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.enums.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsernameAndPassword(String username, String password);
    Optional<ApplicationUser> findByUsername(String username);


    List<ApplicationUser> findAll();
    List<ApplicationUser> findAllByRole(UserRole role);





}
