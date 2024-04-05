package com.swp.birthdaybooking.services;

import com.swp.birthdaybooking.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class BaseService<T, ID> {
    private final JpaRepository<T, ID> repository;

    public BaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public T getById(ID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Entity with id " + id + " not found"));
    }

    public JpaRepository<T, ID> isIdExist(ID id){
        var result = repository.existsById(id);
        if(!result) throw new NotFoundException("Entity with id " + id + " not found");
        return repository;
    }
}
