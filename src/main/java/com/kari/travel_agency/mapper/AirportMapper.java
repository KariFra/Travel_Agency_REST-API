package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.AirportDto;
import com.kari.travel_agency.entity.Airport;
import com.kari.travel_agency.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AirportMapper {

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private AirportRepository repository;

    public Airport toAirport(AirportDto airportDto){
        return new Airport(airportDto.getId(), airportDto.getName(), countryMapper.toCountry(airportDto.getCountry()),
                tripMapper.toTripList(airportDto.getTripsEndingInId()), tripMapper.toTripList(airportDto.getTripsStartingInId()));
    }

    public AirportDto toAirportDto(Airport airport){
        return new AirportDto(airport.getId(), airport.getName(), countryMapper.toCountryDto(airport.getCountry()),
                tripMapper.toTripDtoList(airport.getTripsEndingIn()), tripMapper.toTripDtoList(airport.getTripsStartingIn()));
    }

    public List<Airport> toAirportList(List<Long> list){
        return list.stream()
                .map(element -> repository.getOne(element))
                .collect(Collectors.toList());
    }

    public List<Long> toAirportDtoList(List<Airport> list){
        return list.stream()
                .map(airport -> airport.getId())
                .collect(Collectors.toList());
    }

}
