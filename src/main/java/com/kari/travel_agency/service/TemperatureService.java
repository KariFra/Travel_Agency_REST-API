package com.kari.travel_agency.service;

import com.kari.travel_agency.dto.TemperatureDto;
import com.kari.travel_agency.entity.Temperature;
import com.kari.travel_agency.exception.NotFoundException;
import com.kari.travel_agency.mapper.TemperatureMapper;
import com.kari.travel_agency.repository.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemperatureService {

    @Autowired
    private TemperatureRepository repository;

    @Autowired
    private TemperatureMapper mapper;

    public List<TemperatureDto> getTemperatures(){
        return mapper.toTemperatureDtoList(repository.findAll());
    }

    public TemperatureDto getTemperature(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Temperature with the id number: "+id+" was not found"));
        return mapper.toTemperatureDto(repository.getOne(id));
    }

    public void createTemperature(Temperature temperature){
        Temperature newTemperature = new Temperature(temperature.getDegree(),temperature.getDescription(),
                temperature.getDate(), temperature.getCity());
        repository.save(newTemperature);
    }

    public void deleteTemperature(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Temperature with the id number: "+id+" was not found"));
        repository.deleteById(id);
    }


}
