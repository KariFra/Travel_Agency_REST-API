package com.kari.travelagency.sheduler;

import com.kari.travelagency.client.CocktailClient;
import com.kari.travelagency.dto.CocktailDto;
import com.kari.travelagency.entity.Drink;
import com.kari.travelagency.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//@Scheduled(fixedDelay = 10000)
@Component
public class ScheduleCocktailSave {

    @Autowired
    private CocktailClient client;

    @Autowired
    private DrinkService service;


    @Scheduled(cron = "0 15 10 ? * 5")
    public void cocktailSave(){
        CocktailDto cocktailDto = client.getCocktail();
        Drink drink = new Drink(cocktailDto.getName(), cocktailDto.getRecipe());
        service.save(drink);
        System.out.println("Your schedule is working so good job joung Padavan!");
    }
}
