package com.example.firstproject.repository;

import com.example.firstproject.entity.Coffee;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    @Override
    List<Coffee> findAll();
}
