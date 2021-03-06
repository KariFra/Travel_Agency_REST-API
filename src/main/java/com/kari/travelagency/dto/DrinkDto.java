package com.kari.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DrinkDto {
    private Long id;
    private String name;
    private String recipe;

    @Override
    public String toString() {
        return "DrinkDto{" +
                "name='" + name + '\'' +
                ", recipe='" + recipe + '\'' +
                '}';
    }

    public DrinkDto(String name, String recipe) {
        this.name = name;
        this.recipe = recipe;
    }
}
