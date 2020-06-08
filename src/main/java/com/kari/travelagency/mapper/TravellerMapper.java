package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TravellerMapper {

    @Autowired
    public TripMapper mapper;


    public Traveller toTraveller(TravellerDto travellerDto){
        List<Long> list = travellerDto.getTripsId();
//        List<Trip> trips = list.stream().map(id -> repository.getOne(id)).collect(Collectors.toList());
        return new Traveller().toBuilder()
                .id(travellerDto.getId())
                .firstName(travellerDto.getFirstName())
                .lastName(travellerDto.getLastName())
                .mail(travellerDto.getMail())
                .password(travellerDto.getPassword())
                .role(travellerDto.getRole())
                .avatarUrl(travellerDto.getAvatarUrl())
                .opinions(travellerDto.getOpinions())
                .trips(mapper.toTripList(travellerDto.getTripsId()))
                .build();
    }

    public TravellerDto toTravellerDto(Traveller traveller){
        List<Trip> trips = traveller.getTrips();
        List<Long> longs = trips.stream().map(trip -> trip.getId()).collect(Collectors.toList());
       return new TravellerDto().toBuilder()
                .id(traveller.getId())
                .firstName(traveller.getFirstName())
                .lastName(traveller.getLastName())
                .mail(traveller.getMail())
                .password(traveller.getPassword())
                .role(traveller.getRole())
                .avatarUrl(traveller.getAvatarUrl())
               .opinions(traveller.getOpinions())
               .tripsId(mapper.toTripIdList(traveller.getTrips()))
                .build();
    }

    public List<TravellerDto> toTravellerDtoList(List<Traveller> list){
        return list.stream()
                .map(user -> toTravellerDto(user))
                .collect(Collectors.toList());
    }
}
