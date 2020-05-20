package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.CityDto;
import com.kari.travel_agency.entity.City;
import com.kari.travel_agency.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper {

    @Autowired
    private CountryMapper countryMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TemperatureMapper temperatureMapper;

    @Autowired
    private CityRepository repository;


    public City toCity(CityDto cityDto){
        return  new City(cityDto.getId(), cityDto.getName(), temperatureMapper.toTemperature(cityDto.getTemperature()),
                countryMapper.toCountry(cityDto.getCountry()), articleMapper.toArticleList(cityDto.getArticlesId()));
    }

    public CityDto toCityDto(City city){
        return  new CityDto(city.getId(), city.getName(), temperatureMapper.toTemperatureDto(city.getTemperature()),
                countryMapper.toCountryDto(city.getCountry()), articleMapper.toArticleDtoList(city.getArticles()));
    }

    public List<City> toCityList(List<Long> list){
        return list.stream()
                .map(element -> repository.getOne(element))
                .collect(Collectors.toList());
    }

    public List<Long> toCityDtoList(List<City> list){
        return list.stream()
                .map(city -> city.getId())
                .collect(Collectors.toList());
    }



}
