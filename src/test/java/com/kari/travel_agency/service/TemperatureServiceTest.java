package com.kari.travel_agency.service;


import com.kari.travel_agency.dto.TemperatureDto;
import com.kari.travel_agency.entity.Temperature;
import com.kari.travel_agency.exception.NotFoundException;
import com.kari.travel_agency.mapper.TemperatureMapper;
import com.kari.travel_agency.repository.TemperatureRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TemperatureServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureServiceTest.class);

    @Autowired
    private TemperatureRepository repository;

    @Autowired
    private TemperatureService service;

    @Autowired
    private TemperatureMapper mapper;

    private Long temperatureOneId = 0L;

    @Before
    public void prep(){
        LOGGER.info("Putting two temperatures to database.");
        Temperature temperatureOne = new Temperature(26.8,"It is slighty windy", LocalDate.now(),"Warsaw");
        Temperature temperatureTwo = new Temperature(26.8,"It is slighty windy", LocalDate.now(),"Krakow");
        List<Temperature> list = new ArrayList<>();
        list.add(temperatureOne);
        list.add(temperatureTwo);
        repository.saveAll(list);
        temperatureOneId = temperatureOne.getId();
    }

    @Test
    public void shouldGetTemperatures(){
        LOGGER.info("Getting temperatures.");
        //When
        List<TemperatureDto> temperatureDtosList = service.getTemperatures();
        //Than
        assertTrue(temperatureDtosList.size()!=0);
        assertEquals("Warsaw",temperatureDtosList.get(0).getCity());
    }

    @Test
    public void shouldGetArticle(){
        LOGGER.info("Getting one article");
        //When
        List<Temperature> list = repository.findAll();
        TemperatureDto temperatureDto = service.getTemperature(temperatureOneId);

        //Than
        assertEquals("Warsaw",temperatureDto.getCity());
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetUserException() throws NotFoundException{
        //When
        TemperatureDto temperatureDto = service.getTemperature(50L);
    }

    @Test
    public void shouldCreateTemperature(){
        LOGGER.info("Creating temperature");
        //Given
        Temperature temperature = new Temperature(26.8,"It is slighty windy", LocalDate.now(),"Gdansk");
        List<Temperature> listOne = repository.findAll();
        int sizeOne = listOne.size();
        service.createTemperature(temperature);
        //When
        List<Temperature> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();

        //Than
        assertTrue(sizeTwo - sizeOne == 1);
    }

    @Test
    public void shouldDeleteTemperature(){
        LOGGER.info("Deleting all of the temperatures.");
        //When
        List<Temperature> list = repository.findAll();
        for(int i=0; i<list.size();i++) {
            Long num = list.get(i).getId();
            service.deleteTemperature(num);
        }
        //Than
        assertTrue(repository.findAll().size()==0);
    }
}