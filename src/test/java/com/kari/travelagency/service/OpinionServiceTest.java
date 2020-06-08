package com.kari.travelagency.service;

import com.kari.travelagency.dto.OpinionDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.OpinionMapper;
import com.kari.travelagency.repository.OpinionRepository;
import com.kari.travelagency.repository.TravellerRepository;
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
    private Long opinionTwoId = 0L;

    @Before
    public void prep(){
        LOGGER.info("Putting two opinions into database.");

        Opinion opinionOne = new Opinion("It suckskhfkhskuhfusehfus", "traveller", 2);
        Opinion opinionTwo = new Opinion("It is awesomeefkhsefuesfhsehkf!", "traveller", 10);

        List<Opinion> list = new ArrayList<>();
        list.add(opinionOne);
        list.add(opinionTwo);
        repository.saveAll(list);
        opinionOneId = opinionOne.getId();
        opinionTwoId = opinionTwo.getId();
    }

    @Test
    public void shouldGetOpinions(){
        LOGGER.info("Getting all opinions");
        //When
        List<OpinionDto> list = service.getOpinions();
        //Than
        assertTrue(list.size()>= 1);
        //Cleanup
        repository.deleteById(opinionOneId);
        repository.deleteById(opinionTwoId);
    }

    @Test
    public void shouldGetOpinion(){
        LOGGER.info("Getting one opinion");
        //When
        Opinion opinion = mapper.toOpinion(service.getOpinion(opinionOneId));
        //Than
        assertEquals(2,opinion.getRating());
        //Cleanup
        repository.deleteById(opinionOneId);
        repository.deleteById(opinionTwoId);
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetTripException() throws NotFoundException{
        //When
        OpinionDto opinionDto = service.getOpinion(50000L);
        //Cleanup
        repository.deleteById(opinionOneId);
        repository.deleteById(opinionTwoId);
    }

    @Test
    public void shouldCreateTrip(){
        LOGGER.info("Creating opinion");
        //Given

        Opinion opinion = new Opinion("I spend nice timesjefsjkefskehf", "traveller", 8);
        List<Opinion> listOne = repository.findAll();
        int sizeOne = listOne.size();
        //When
        Long fourthOpinion = service.createNewOpinion(opinion).getId();
        List<Opinion> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();

        //Than
        assertTrue(sizeTwo - sizeOne == 1);
        //Cleanup
        repository.deleteById(opinionOneId);
        repository.deleteById(opinionTwoId);
        repository.deleteById(fourthOpinion);
    }

    @Test
    public void shouldDeleteOpinion(){
        LOGGER.info("Deleting all of the opinions.");
        //Given

        Opinion opinion = new Opinion("I spend nice timjshfksuehfsehfkue", "traveller", 8);

        //When
        Long fourthOpinion = repository.save(opinion).getId();
        List<Opinion> listOne = repository.findAll();
        int sizeOne = listOne.size();
        service.deleteOpinion(fourthOpinion);
        List<Opinion> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();

        //Than
        assertTrue(sizeOne - sizeTwo == 1);
        //Cleanup
        repository.deleteById(opinionOneId);
        repository.deleteById(opinionTwoId);

    }

}