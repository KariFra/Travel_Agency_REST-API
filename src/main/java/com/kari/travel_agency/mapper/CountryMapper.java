package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.CountryDto;
import com.kari.travel_agency.entity.Country;
import com.kari.travel_agency.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountryMapper {

    @Autowired
    private AirportMapper airportMapper;

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private CountryRepository repository;

    public Country toCountry(CountryDto countryDto){
        return new Country(countryDto.getId(), countryDto.getName(), cityMapper.toCityList(countryDto.getCitiesId()),
                airportMapper.toAirportList(countryDto.getAirports()), countryDto.getCurrency());
    }

    public CountryDto toCountryDto(Country country){
        return new CountryDto(country.getId(), country.getName(), cityMapper.toCityDtoList(country.getCities()),
                airportMapper.toAirportDtoList(country.getAirports()), country.getCurrency());
    }

    public List<Country> toCountryList(List<Long> list){
        return list.stream()
                .map(element -> repository.getOne(element))
                .collect(Collectors.toList());
    }

    public List<Long> toCountryDtoList(List<Country> list){
        return list.stream()
                .map(country -> country.getId())
                .collect(Collectors.toList());
    }
}
