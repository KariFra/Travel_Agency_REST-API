package com.kari.travelagency.controller;

import com.kari.travelagency.dto.DrinkDto;
import com.kari.travelagency.mapper.DrinkMapper;
import com.kari.travelagency.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/drink")
public class DrinkController {

    @Autowired
    private DrinkService service;

    @Autowired
    private DrinkMapper mapper;

    @GetMapping
    public List<DrinkDto> getDrinks(){
        return service.getDrinks();
    }

    @GetMapping("/{id}")
    public DrinkDto getDrink(@PathVariable Long id){
        return service.getDrink(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DrinkDto saveDrink(@RequestBody DrinkDto drinkDto){
        return mapper.toDrinkDto(service.save(mapper.toDrink(drinkDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteDrink(@PathVariable Long id) {service.deleteDrink(id);}

}
