package com.kari.travelagency.mapper;


import com.kari.travelagency.dto.DrinkDto;
import com.kari.travelagency.entity.Drink;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DrinkMapper {

    public Drink toDrink(DrinkDto drinkDto){
        return new Drink(drinkDto.getId(), drinkDto.getName(), drinkDto.getRecipe());
    }

    public DrinkDto toDrinkDto(Drink drink){
        return new DrinkDto(drink.getId(), drink.getName(), drink.getRecipe());
    }

    public List<DrinkDto> toDrinkDtoList(List<Drink> list){
        return list.stream()
                .map(drink -> toDrinkDto(drink))
                .collect(Collectors.toList());
    }
}
