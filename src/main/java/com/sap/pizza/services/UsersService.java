package com.sap.pizza.services;

import com.sap.pizza.entities.ApplicationUser;
import com.sap.pizza.exceptions.UserNotFoundException;
import com.sap.pizza.interfaces.IDAOService;
import com.sap.pizza.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements IDAOService<ApplicationUser> {

    private final UsersRepository repo;

    @Autowired
    public UsersService(UsersRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ApplicationUser> list() {
        return repo.findAll();
    }

    @Override
    public ApplicationUser get(int id) throws UserNotFoundException {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("ApplicationUser with id: %d not found!!", id)));
    }

    public ApplicationUser findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        return repo.findByUsernameAndPassword(username, password).orElseThrow(() -> new UserNotFoundException(String.format("ApplicationUser with username: %s not found!!", username)));
    }

    public ApplicationUser findByUsername(String username ) {
        return repo.findByUsername(username).orElse( null);
    }

    @Override
    public ApplicationUser save(ApplicationUser applicationUser) {
        return repo.save(applicationUser);
    }

    @Override
    public void delete(ApplicationUser applicationUser) {
        repo.delete(applicationUser);
    }

    @Override
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Override
    public long count() {
        return repo.count();
    }
}
