package com.kari.travel_agency.controller;


import com.kari.travel_agency.dto.TemperatureDto;
import com.kari.travel_agency.mapper.TemperatureMapper;
import com.kari.travel_agency.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/temperatures")
public class TemperatureController {

    @Autowired
    private TemperatureService service;

    @Autowired
    private TemperatureMapper mapper;

    @GetMapping
    public List<TemperatureDto> getTemperatures(){
        return service.getTemperatures();
    }

    @GetMapping(path = "/{id}")
    public TemperatureDto getTemperature(@PathVariable Long id){
        return service.getTemperature(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewTemperature(@RequestBody TemperatureDto temperatureDto){
        service.createTemperature(mapper.toTemperature(temperatureDto));
    }

    @DeleteMapping(path = "/{id}")
    public void deleteTemperature(@PathVariable Long id){
        service.deleteTemperature(id);
    }


}
