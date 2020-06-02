package com.kari.travelagency.controller;


import com.kari.travelagency.client.CocktailClient;
import com.kari.travelagency.dto.DrinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/cocktail")
public class CocktailController {

    @Autowired
    private CocktailClient cocktailClient;

    @GetMapping
    public DrinkDto getCocktail(){
     return cocktailClient.getCocktail();
    }
}
