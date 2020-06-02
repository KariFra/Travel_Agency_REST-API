package com.kari.travelagency.service;

import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.TravellerMapper;
import com.kari.travelagency.repository.TravellerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TravellerServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TravellerServiceTest.class);

    @Autowired
    private TravellerRepository repository;

    @Autowired
    private TravellerService service;

    @Autowired
    private TravellerMapper mapper;

    private Long userOneId = 0L;
    private Long userTwoId = 0L;

    @Before
    public void prep(){
        LOGGER.info("Putting two users to database.");
        Traveller travellerOne = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        Traveller travellerTwo = new Traveller().toBuilder()
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:ball.svg")
                .build();
        List<Traveller> list = new ArrayList<>();
        list.add(travellerOne);
        list.add(travellerTwo);
        repository.saveAll(list);
        userOneId = travellerOne.getId();
        userTwoId = travellerTwo.getId();
    }

    @Test
    public void shouldGetUsers(){
        LOGGER.info("Getting users.");
        //When
        List<TravellerDto> userList = service.getUsers();
        System.out.println(userList);
        //Than
        assertTrue(userList.size()!=0);
        assertEquals("Mroz",userList.get(1).getLastName());
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
    }

    @Test
    public void shouldGetUser(){
        LOGGER.info("Getting one user");
        //When
        List<Traveller> list = repository.findAll();
        TravellerDto user = service.getUser(userOneId);

        //Than
        assertEquals("Joanna",user.getFirstName());
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetUserException() throws NotFoundException{
        //When
        TravellerDto user = service.getUser(500000L);
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
    }

    @Test
    public void shouldUpdateUser(){
        LOGGER.info("Updating user.");
        //Given
        Traveller beforeTraveller = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        repository.save(beforeTraveller);
        Long num = beforeTraveller.getId();
        //When
        beforeTraveller.setFirstName("July");
        beforeTraveller.setLastName("Bard-Dell");
        beforeTraveller.setMail("user@mail.com");
        service.updateUser(mapper.toUserDto(beforeTraveller));
        //Than
        assertEquals("July",repository.getOne(num).getFirstName());
        assertEquals("Bard-Dell",repository.getOne(num).getLastName());
        assertEquals("user@mail.com",repository.getOne(num).getMail());
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
        repository.deleteById(num);
    }

    @Test
    public void shouldCreateUser(){
        LOGGER.info("Creating user");
        //Given
        Traveller traveller = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        List<Traveller> listOne = repository.findAll();
        int sizeOne = listOne.size();
        Long thirdUserId = service.createNewUser(traveller).getId();
        //When
        List<Traveller> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();

        //Than
        assertTrue(sizeTwo - sizeOne == 1);
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
        repository.deleteById(thirdUserId);
    }

    @Test
    public void shouldDeleteUser(){
        LOGGER.info("Deleting all of the users.");
        //Given
        Traveller traveller = new Traveller().toBuilder()
                .firstName("Dereck")
                .lastName("Mroz")
                .mail("user2@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        Long userId = repository.save(traveller).getId();
        List<Traveller> listOne = repository.findAll();
        //When
        int sizeOne = listOne.size();
        service.deleteUser(userId);
        List<Traveller> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();
        //Than
        assertTrue(sizeOne - sizeTwo == 1);
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);

    }

}