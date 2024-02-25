package com.example.basicSpringBoot.dto;

import com.example.basicSpringBoot.entity.Coffee;
import lombok.*;

@AllArgsConstructor
@ToString
public class CoffeeDto {
    private Long id;
    private String name;
    private String price;

    public Coffee toEntity(){
        return new Coffee(id, name, price);
    }
}
