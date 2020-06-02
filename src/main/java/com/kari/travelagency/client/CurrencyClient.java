package com.kari.travelagency.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kari.travelagency.dto.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CurrencyClient {

    @Value("${nbp.api.endpoint.prod}")
    private String apiEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    private String code;
    private String rate;

    public CurrencyDto getRate(String currency) {
        String result = restTemplate.getForObject(apiEndpoint + currency + "/?format=json",String.class);
        JsonObject jsonObject = JsonParser.parseString(result).getAsJsonObject();
        code = jsonObject.get("code").getAsString();
        JsonArray array = jsonObject.getAsJsonArray("rates");
        for (int i = 0; i < array.size(); i++) {
            rate = array.get(i).getAsJsonObject().get("mid").getAsString();
        }
     return new CurrencyDto(code,rate);

    }
}
