package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.TripDto;
import com.kari.travel_agency.entity.Trip;
import com.kari.travel_agency.entity.User;
import com.kari.travel_agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripMapper {


    public Trip toTrip(TripDto tripDto){
        return new Trip(tripDto.getId(), tripDto.getArrivedAirport(), tripDto.getDepartedAirport(), tripDto.getAmountOfParticipants(),
                tripDto.getPrice(), tripDto.getFoodOption(), tripDto.getDescription(), tripDto.getStandard(), tripDto.getLength(),
                tripDto.getStartingTime(), tripDto.getFinishTime(), tripDto.getPhotoUrl());
    }

    public TripDto toTripDto(Trip trip){
        return new TripDto(trip.getId(), trip.getArrivedAirport(), trip.getDepartedAirport(), trip.getAmountOfParticipants(),
                trip.getPrice(), trip.getFoodOption(), trip.getDescription(), trip.getStandard(), trip.getLength(),
                trip.getStartingTime(), trip.getFinishTime(), trip.getPhotoUrl());
    }

    public List<TripDto> toTripDtoListWhole(List<Trip> list){
        return list.stream()
                .map(trip -> toTripDto(trip))
                .collect(Collectors.toList());
    }
}
