package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.TripDto;
import com.kari.travel_agency.entity.Trip;
import com.kari.travel_agency.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OpinionMapper opinionMapper;

    @Autowired
    private AirportMapper airportMapper;

    @Autowired
    private TripRepository repository;

    public Trip toTrip(TripDto tripDto){
        return new Trip(tripDto.getId(), airportMapper.toAirport(tripDto.getArrived()), airportMapper.toAirport(tripDto.getDeparted()), userMapper.toUser(tripDto.getUser()), tripDto.getAmountOfParticipants(),
                tripDto.getPrice(), tripDto.getFoodOption(), tripDto.getStandard(), tripDto.getLength(), tripDto.getStartingTime(),
                tripDto.getFinishTime(), tripDto.getPhotoUrl(), opinionMapper.toOpinion(tripDto.getOpinion()), tripDto.isComplaint());
    }

    public TripDto toTripDto(Trip trip){
        return new TripDto(trip.getId(), airportMapper.toAirportDto(trip.getArrived()), airportMapper.toAirportDto(trip.getDeparted()), userMapper.toUserDto(trip.getUser()),
                trip.getAmountOfParticipants(), trip.getPrice(), trip.getFoodOption(), trip.getStandard(), trip.getLength(),
                trip.getStartingTime(), trip.getFinishTime(), trip.getPhotoUrl(), opinionMapper.toOpinionDto(trip.getOpinion()), trip.isComplaint());
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
}
