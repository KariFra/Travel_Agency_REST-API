package com.kari.travelagency.controller;

import com.kari.travelagency.client.CurrencyClient;
import com.kari.travelagency.dto.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/currency")
public class CurrencyController {

    @Autowired
    CurrencyClient currencyClient;

    @GetMapping("/{currency}")
    public CurrencyDto getCurrency(@PathVariable String currency){
        return currencyClient.getRate(currency);
    }
}
