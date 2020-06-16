package com.kari.travelagency.controller;

import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.mapper.TravellerMapper;
import com.kari.travelagency.repository.TravellerRepository;
import com.kari.travelagency.service.TravellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/travellers")
public class TravellerController {

    @Autowired
    private TravellerService travellerService;

    @Autowired
    private TravellerRepository repository;

    @Autowired
    private TravellerMapper mapper;


    @GetMapping
    public List<TravellerDto> getUsers(){
        return travellerService.getUsers();
    }

    @GetMapping("/{id}")
    public TravellerDto getUser(@PathVariable Long id){
        return travellerService.getUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody TravellerDto user){
        travellerService.createNewUser(mapper.toTraveller(user));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TravellerDto update(@RequestBody TravellerDto user){
        return travellerService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        travellerService.deleteUser(id);
    }

}
