package com.kari.travelagency.service;

import com.kari.travelagency.dto.DrinkDto;
import com.kari.travelagency.entity.Drink;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.DrinkMapper;
import com.kari.travelagency.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinkService {

    @Autowired
    private DrinkRepository repository;

    @Autowired
    private DrinkMapper mapper;

    public List<DrinkDto> getDrinks(){
        return mapper.toDrinkDtoList(repository.findAll());
    }

    public DrinkDto getDrink(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Drink with the id number: "
                +id+" was not found"));
        return mapper.toDrinkDto(repository.getOne(id));
    }

    public Drink save(Drink drink){
        Drink newDrink = new Drink(drink.getName(),drink.getRecipe());
        return repository.save(newDrink);
    }

    public void deleteDrink(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Drink with the id number: "
                +id+" was not found"));
        repository.deleteById(id);
    }
}
