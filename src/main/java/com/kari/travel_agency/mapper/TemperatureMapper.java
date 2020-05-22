package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.TemperatureDto;
import com.kari.travel_agency.entity.Temperature;
import com.kari.travel_agency.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TemperatureMapper {


    @Autowired
    private TemperatureRepository repository;

    public Temperature toTemperature(TemperatureDto temperatureDto){
        return new Temperature(temperatureDto.getId(), temperatureDto.getDegree(), temperatureDto.getDescription(),
                temperatureDto.getUrl(), temperatureDto.getDateBeginning(), temperatureDto.getAmountOfDays(),
                temperatureDto.getCity());
    }

    public TemperatureDto toTemperatureDto(Temperature temperature){
        return new TemperatureDto(temperature.getId(), temperature.getDegree(), temperature.getDescription(),
                temperature.getUrl(), temperature.getDateBeginning(), temperature.getAmountOfDays(),
                temperature.getCity());
    }

    public List<Temperature> toTemperatureList(List<Long> list){
        return list.stream()
                .map(element-> repository.getOne(element))
                .collect(Collectors.toList());
    }

    public List<Long> toTemperatureDtoList(List<Temperature> list){
        return list.stream()
                .map(temperature -> temperature.getId())
                .collect(Collectors.toList());
    }
}
