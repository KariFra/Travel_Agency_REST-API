package com.kari.travelagency.controller;

import com.kari.travelagency.dto.TripDto;
import com.kari.travelagency.mapper.TripMapper;
import com.kari.travelagency.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trips")
public class TripController {

    @Autowired
    private TripService service;

    @Autowired
    private TripMapper mapper;

    @GetMapping
    public List<TripDto> getTrips(){
        return service.getTrips();
    }

    @GetMapping("/{id}")
    public TripDto getTrip(@PathVariable Long id){
        return service.getTrip(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createTrip(@RequestBody TripDto tripDto){
        System.out.println(tripDto);
        service.createNewTrip(mapper.toTrip(tripDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TripDto updateTrip(@RequestBody TripDto tripDto){
        TripDto tripDto1 = service.updateTrip(tripDto);
        return tripDto1;
    }

    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable Long id){
        service.deleteTrip(id);
    }
}
