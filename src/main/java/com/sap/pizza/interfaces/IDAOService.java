package com.sap.pizza.interfaces;

import com.sap.pizza.exceptions.EntityNotFoundException;

import java.util.List;

public interface IDAOService<T> {

    List<T> list();

    T get(int id) throws EntityNotFoundException;

    T save(T t);

    void delete(T t) ;

    void deleteById(int id) ;

    long count();
}
