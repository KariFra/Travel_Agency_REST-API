package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.TripDto;
import com.kari.travel_agency.dto.UserDto;
import com.kari.travel_agency.entity.Trip;
import com.kari.travel_agency.entity.User;
import com.kari.travel_agency.repository.TripRepository;
import com.kari.travel_agency.repository.UserRepository;
import com.kari.travel_agency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripMapper {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private TripRepository repository;

    public Trip toTrip(TripDto tripDto){
        User user = userRepository.getOne(tripDto.getUserId());
        return new Trip(tripDto.getId(), tripDto.getArrivedAirport(), tripDto.getDepartedAirport(), user);
    }

    public TripDto toTripDto(Trip trip){
        return new TripDto(trip.getId(), trip.getArrivedAirport(), trip.getDepartedAirport(), trip.getUser().getId());
    }

    public List<Trip> toTripList(List<Long> list){
        return list.stream()
                .map(element -> repository.getOne(element))
                .collect(Collectors.toList());
    }

    public List<Long> toTripDtoList(List<Trip> list){
        return list.stream()
                .map(trip -> trip.getId())
                .collect(Collectors.toList());
    }
    public List<TripDto> toTripDtoListWhole(List<Trip> list){
        return list.stream()
                .map(trip -> toTripDto(trip))
                .collect(Collectors.toList());
    }
}
