package com.kari.travelagency.service;

import com.kari.travelagency.dto.OpinionDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.entity.UserEntity;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.OpinionMapper;
import com.kari.travelagency.repository.OpinionRepository;
import com.kari.travelagency.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    private Long opinionOneId = 0L;
    private Long opinionTwoId = 0L;
    private Long userId = 0L;

    @Before
    public void prep(){
        LOGGER.info("Putting two opinions into database.");

        UserEntity userEntity = new UserEntity().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user2@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        userId = userRepository.save(userEntity).getId();

        Opinion opinionOne = new Opinion("It suckskhfkhskuhfusehfus", userEntity, 2);
        Opinion opinionTwo = new Opinion("It is awesomeefkhsefuesfhsehkf!", userEntity, 10);

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
        userRepository.deleteById(userId);
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
        userRepository.deleteById(userId);
        repository.deleteById(opinionOneId);
        repository.deleteById(opinionTwoId);
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetTripException() throws NotFoundException{
        //When
        OpinionDto opinionDto = service.getOpinion(50000L);
        //Cleanup
        userRepository.deleteById(userId);
        repository.deleteById(opinionOneId);
        repository.deleteById(opinionTwoId);
    }

    @Test
    public void shouldCreateTrip(){
        LOGGER.info("Creating opinion");
        //Given
        UserEntity userEntity = new UserEntity().toBuilder()
                .firstName("Dereck")
                .lastName("Mroz")
                .mail("user2@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        Long userTwoId = userRepository.save(userEntity).getId();

        Opinion opinion = new Opinion("I spend nice timesjefsjkefskehf", userEntity, 8);
        List<Opinion> listOne = repository.findAll();
        int sizeOne = listOne.size();
        //When
        Long fourthOpinion = service.createNewOpinion(opinion).getId();
        List<Opinion> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();

        //Than
        assertTrue(sizeTwo - sizeOne == 1);
        //Cleanup
        userRepository.deleteById(userId);
        userRepository.deleteById(userTwoId);
        repository.deleteById(opinionOneId);
        repository.deleteById(opinionTwoId);
        repository.deleteById(fourthOpinion);
    }

    @Test
    public void shouldDeleteOpinion(){
        LOGGER.info("Deleting all of the opinions.");
        //Given
        UserEntity userEntity = new UserEntity().toBuilder()
                .firstName("Dereck")
                .lastName("Mroz")
                .mail("user2@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        Long userTwoId = userRepository.save(userEntity).getId();

        Opinion opinion = new Opinion("I spend nice timjshfksuehfsehfkue", userEntity, 8);

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
        userRepository.deleteById(userId);
        userRepository.deleteById(userTwoId);
        repository.deleteById(opinionOneId);
        repository.deleteById(opinionTwoId);

    }

}