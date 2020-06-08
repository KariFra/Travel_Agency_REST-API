package com.kari.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CocktailDto {

    private String name;
    private String recipe;

    @Override
    public String toString() {
        return "CocktailDto{" +
                "name='" + name + '\'' +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
