package com.example.basicSpringBoot.repository;

import com.example.basicSpringBoot.entity.Coffee;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    ArrayList<Coffee> findAll();
}
