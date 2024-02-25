package com.example.basicSpringBoot.api;

import com.example.basicSpringBoot.entity.Coffee;
import com.example.basicSpringBoot.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class CoffeeController {
    @Autowired
    private CoffeeRepository coffeeRepository;
    @GetMapping("/api/coffees")
    public ResponseEntity<ArrayList<Coffee>> index(){
        return ResponseEntity.status(HttpStatus.OK).body(coffeeRepository.findAll());
    }
}
