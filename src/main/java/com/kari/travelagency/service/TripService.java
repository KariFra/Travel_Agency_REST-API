package com.kari.travelagency.service;

import com.kari.travelagency.dto.TripDto;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.TripMapper;
import com.kari.travelagency.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository repository;


    @Autowired
    private TripMapper mapper;

    public List<TripDto> getTrips(){
        return mapper.toTripDtoListWhole(repository.findAll());
    }

    public TripDto getTrip(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Trip with the id number: "+id+" was not found"));
        return mapper.toTripDto(repository.getOne(id));
    }


    public void createNewTrip(Trip trip){
        Trip newTrip = new Trip().toBuilder()
                .price(trip.getPrice())
                .userEntity(null)
                .city(trip.getCity())
                .url(trip.getUrl())
                .description(trip.getDescription())
                .length(trip.getLength())
                .additions(null)
                .build();
        repository.save(newTrip);
    }


    public TripDto updateTrip(TripDto tripDto){
        Trip newTrip = repository.getOne(tripDto.getId());
        newTrip.setPrice(tripDto.getPrice());
        newTrip.setCity(tripDto.getCity());
        newTrip.setUrl(tripDto.getUrl());
        newTrip.setDescription(tripDto.getDescription());
        newTrip.setLength(tripDto.getLength());
        newTrip.setAdditions(tripDto.getAdditions());

        return mapper.toTripDto(repository.save(newTrip));
    }

    public void deleteTrip(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Trip with the id number: "+id+" was not found"));
        repository.deleteById(id);
    }
}
