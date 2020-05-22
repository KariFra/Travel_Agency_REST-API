package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.TemperatureDto;
import com.kari.travel_agency.entity.Temperature;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TemperatureMapper {


    public Temperature toTemperature(TemperatureDto temperatureDto){
        return new Temperature(temperatureDto.getId(), temperatureDto.getDegree(), temperatureDto.getDescription(),
                 temperatureDto.getDate(), temperatureDto.getCity());
    }

    public TemperatureDto toTemperatureDto(Temperature temperature){
        return new TemperatureDto(temperature.getId(), temperature.getDegree(), temperature.getDescription(),
                 temperature.getDate(), temperature.getCity());
    }


    public List<TemperatureDto> toTemperatureDtoList(List<Temperature> list){
        return list.stream()
                .map(temperature -> toTemperatureDto(temperature))
                .collect(Collectors.toList());
    }
}
