package com.kari.travel_agency.service;

import com.kari.travel_agency.dto.TripDto;
import com.kari.travel_agency.entity.Trip;
import com.kari.travel_agency.exception.NotFoundException;
import com.kari.travel_agency.mapper.TripMapper;
import com.kari.travel_agency.repository.TripRepository;
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
        Trip newTrip = new Trip(trip.getArrivedAirport(), trip.getDepartedAirport(), trip.getAmountOfParticipants(),
                trip.getPrice(), trip.getFoodOption(), trip.getDescription(), trip.getStandard(), trip.getLength(),
                trip.getStartingTime(), trip.getFinishTime(), trip.getPhotoUrl());
        repository.save(newTrip);
    }

    public TripDto updateTrip(TripDto tripDto){
        Trip newTrip = repository.getOne(tripDto.getId());
        newTrip.setComplaint(tripDto.isComplaint());
        newTrip.setDescription(tripDto.getDescription());
        newTrip.setFoodOption(tripDto.getFoodOption());
        newTrip.setFinishTime(tripDto.getFinishTime());
        newTrip.setStartingTime(tripDto.getStartingTime());
        newTrip.setStandard(tripDto.getStandard());
        return mapper.toTripDto(repository.save(newTrip));
    }

    public void deleteTrip(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Trip with the id number: "+id+" was not found"));
        repository.deleteById(id);
    }
}
