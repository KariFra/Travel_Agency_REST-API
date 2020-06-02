package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.TripDto;
import com.kari.travelagency.entity.Trip;
import org.springframework.stereotype.Component;



import java.util.List;
import java.util.stream.Collectors;

@Component
public class TripMapper {



    public Trip toTrip(TripDto tripDto){
        return new Trip().toBuilder()
                .id(tripDto.getId())
                .price(tripDto.getPrice())
                .city(tripDto.getCity())
                .url(tripDto.getUrl())
                .description(tripDto.getDescription())
                .length(tripDto.getLength())
                .additions(tripDto.getAdditions())
                .build();
    }

    public TripDto toTripDto(Trip trip){
        return new TripDto().toBuilder()
                .id(trip.getId())
                .price(trip.getPrice())
                .city(trip.getCity())
                .url(trip.getUrl())
                .description(trip.getDescription())
                .length(trip.getLength())
                .additions(trip.getAdditions())
                .build();
    }

    public List<TripDto> toTripDtoListWhole(List<Trip> list){
        return list.stream()
                .map(trip -> toTripDto(trip))
                .collect(Collectors.toList());
    }
}
