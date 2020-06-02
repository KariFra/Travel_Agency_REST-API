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
    private String name;
    private String recipe;

    @Override
    public String toString() {
        return "DrinkDto{" +
                "name='" + name + '\'' +
                ", recipe='" + recipe + '\'' +
                '}';
    }
}
