package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.TemperatureDto;
import com.kari.travel_agency.entity.Temperature;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TemperatureMapperTest {

    @Test
    public void mapToTemperatureDtoAndBack(){
        //Given
        Temperature temperature = new Temperature(26.8,"It is slighty windy", LocalDate.now(),"Warsaw");
        TemperatureMapper mapper = new TemperatureMapper();

        //When
        TemperatureDto temperatureDto = mapper.toTemperatureDto(temperature);
        Temperature newTemperature = mapper.toTemperature(temperatureDto);

        //Than
        assertEquals(26.8,temperatureDto.getDegree(),0.5);
        assertEquals("Warsaw",newTemperature.getCity());

    }

    @Test
    public void mapToTemperatureDtoList(){
        //Given
        Temperature temperatureOne = new Temperature(26.8,"It is slighty windy", LocalDate.now(),"Warsaw");
        Temperature temperatureTwo = new Temperature(45.8,"We are burning", LocalDate.now(),"Stuttgart");
        List<Temperature> list = new ArrayList<>();
        list.add(temperatureOne);
        list.add(temperatureTwo);
        TemperatureMapper mapper = new TemperatureMapper();

        //When
        List<TemperatureDto> dtoList = mapper.toTemperatureDtoList(list);

        //Than
        assertEquals(26.8,dtoList.get(0).getDegree(),0.5);


    }


}