package com.kari.travelagency.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kari.travelagency.dto.DrinkDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CocktailClient {

    @Value("${coctail.api.endpoint.prod}")
    private String apiEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    private String drinkName;
    private String drinkRecipe;

    public DrinkDto getCocktail() {
        String result = restTemplate.getForObject(apiEndpoint, String.class);
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        JsonArray array = jsonObject.getAsJsonArray("drinks");
        for (int i = 0; i < array.size(); i++) {
            drinkName = array.get(i).getAsJsonObject().get("strDrink").getAsString();
            drinkRecipe = array.get(i).getAsJsonObject().get("strInstructions").getAsString();
        }
        return new DrinkDto(drinkName,drinkRecipe);
    }


}
