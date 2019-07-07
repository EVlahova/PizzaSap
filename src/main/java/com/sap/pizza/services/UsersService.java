package com.sap.pizza.services;

import com.sap.pizza.entities.User;
import com.sap.pizza.exceptions.UserNotFoundException;
import com.sap.pizza.interfaces.IDAOService;
import com.sap.pizza.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService implements IDAOService<User> {

    private final UsersRepository repo;

    @Autowired
    public UsersService(UsersRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<User> list() {
        return repo.findAll();
    }

    @Override
    public User get(int id) throws UserNotFoundException {
        return repo.findById(id).orElseThrow(() -> new UserNotFoundException(String.format("User with id: %d not found!!", id)));
    }

    public User findByUsernameAndPassword( String username, String password) throws UserNotFoundException {
        return repo.findByUsernameAndPassword(username, password).orElseThrow(() -> new UserNotFoundException(String.format("User with username: %s not found!!", username)));
    }

    public User findByUsername( String username ) {
        return repo.findByUsername(username).orElse( null);
    }

    @Override
    public User save(User user) {
        return repo.save(user);
    }

    @Override
    public void delete(User user) {
        repo.delete(user);
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
