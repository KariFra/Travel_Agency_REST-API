package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.TripDto;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.repository.TravellerRepository;
import com.kari.travelagency.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripMapper {

    @Autowired
    private TripRepository repository;

    @Autowired
    private TravellerRepository travellerRepository;

    private Traveller traveller;
    private Long id;

    public Trip toTrip(TripDto tripDto){
        if(tripDto.getUserId() != null){
            traveller = travellerRepository.getOne(tripDto.getUserId());}
        else{
            traveller = null;
        }
        return new Trip().toBuilder()
                .id(tripDto.getId())
                .price(tripDto.getPrice())
                .traveller(traveller)
                .city(tripDto.getCity())
                .url(tripDto.getUrl())
                .description(tripDto.getDescription())
                .length(tripDto.getLength())
                .food(tripDto.getFood())
                .stars(tripDto.getStars())
                .additions(tripDto.getAdditions())
                .build();
    }

    public TripDto toTripDto(Trip trip){
        if(trip.getTraveller() != null){
            id = trip.getTraveller().getId();}
        else{
            id = null;
        }
        return new TripDto().toBuilder()
                .id(trip.getId())
                .price(trip.getPrice())
                .userId(id)
                .city(trip.getCity())
                .url(trip.getUrl())
                .description(trip.getDescription())
                .length(trip.getLength())
                .food(trip.getFood())
                .stars(trip.getStars())
                .additions(trip.getAdditions())
                .build();
    }

    public List<TripDto> toTripDtoListWhole(List<Trip> list){
        return list.stream()
                .map(trip -> toTripDto(trip))
                .collect(Collectors.toList());
    }

    public List<Trip> toTripList (List<Long> list){
        return list.stream()
                .map(id -> repository.getOne(id))
                .collect(Collectors.toList());
    }

    public List<Long> toTripIdList(List<Trip> list){
        System.out.println(list);
        return list.stream()
                .map(trip -> trip.getId())
                .collect(Collectors.toList());
    }
}
