package com.kari.travel_agency.service;

import com.kari.travel_agency.dto.OpinionDto;
import com.kari.travel_agency.entity.Opinion;
import com.kari.travel_agency.exception.NotFoundException;
import com.kari.travel_agency.mapper.OpinionMapper;
import com.kari.travel_agency.repository.OpinionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OpinionServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OpinionServiceTest.class);

    @Autowired
    private OpinionService service;

    @Autowired
    private OpinionMapper mapper;

    @Autowired
    private OpinionRepository repository;

    private Long opinionOneId = 0L;

    @Before
    public void prep(){
        LOGGER.info("Putting two opinions into database.");
        Opinion opinionOne = new Opinion("It sucks",2);
        Opinion opinionTwo = new Opinion("It is awesome!",10);

        List<Opinion> list = new ArrayList<>();
        list.add(opinionOne);
        list.add(opinionTwo);
        repository.saveAll(list);
        opinionOneId = opinionOne.getId();
    }

    @Test
    public void shouldGetOpinions(){
        LOGGER.info("Getting all opinions");
        //When
        List<OpinionDto> list = service.getOpinions();
        //Than
        assertEquals(2,list.get(1).getRating());
    }

    @Test
    public void shouldGetOpinion(){
        LOGGER.info("Getting one opinion");
        //When
        Opinion opinion = mapper.toOpinion(service.getOpinion(opinionOneId));
        //Than
        assertEquals(2,opinion.getRating());
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetTripException() throws NotFoundException{
        //When
        OpinionDto opinionDto = service.getOpinion(50L);
    }

    @Test
    public void shouldCreateTrip(){
        LOGGER.info("Creating opinion");
        //Given
        Opinion opinion = new Opinion("I spend nice time",8);
        List<Opinion> listOne = repository.findAll();
        int sizeOne = listOne.size();
        service.createNewOpinion(opinion);
        //When
        List<Opinion> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();

        //Than
        assertTrue(sizeTwo - sizeOne == 1);
    }

    @Test
    public void shouldDeleteOpinion(){
        LOGGER.info("Deleting all of the opinions.");
        //When
        List<Opinion> list = repository.findAll();
        for(int i=0; i<list.size();i++) {
            Long num = list.get(i).getId();
            service.deleteOpinion(num);
        }
        //Than
        assertTrue(repository.findAll().size()==0);
    }

}