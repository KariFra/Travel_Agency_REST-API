package com.kari.travelagency.controller;

import com.kari.travelagency.client.WeatherClient;
import com.kari.travelagency.dto.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    @Autowired
    private WeatherClient weatherClient;

    @GetMapping("/{city}")
    public WeatherDto[] getWeatherForSixDays(@PathVariable String city){
        return weatherClient.getWeatherDays(city);
    }
    @GetMapping("/now/{city}")
    public WeatherDto getWeatherForOneDay(@PathVariable String city){
        return weatherClient.getWeatherDay(city);
    }
}
