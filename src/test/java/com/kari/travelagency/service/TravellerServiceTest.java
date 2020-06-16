package com.kari.travelagency.service;

import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.TravellerMapper;
import com.kari.travelagency.repository.TravellerRepository;
import com.kari.travelagency.repository.TripRepository;
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
    private TripRepository tripRepository;

    @Autowired
    private TravellerService service;

    @Autowired
    private TravellerMapper mapper;

    private Long userOneId = 0L;
    private Long userTwoId = 0L;

    private List<String> opinions = new ArrayList<>();
    private List<Trip> trips = new ArrayList<>();

    @Test
    public void shouldGetTravellers(){
        //Given
        LOGGER.info("Putting two users to database.");
        Traveller travellerOne = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
                .build();
        Traveller travellerTwo = new Traveller().toBuilder()
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
                .build();
        List<Traveller> list = new ArrayList<>();
        list.add(travellerOne);
        list.add(travellerTwo);
        repository.saveAll(list);
        userOneId = travellerOne.getId();
        userTwoId = travellerTwo.getId();
        LOGGER.info("Getting users.");
        //When
        List<TravellerDto> userList = service.getUsers();
        System.out.println(userList);
        //Than
        assertTrue(userList.size()>=2);
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
    }

    @Test
    public void shouldGetTraveller(){
        //Given
        LOGGER.info("Putting two users to database.");
        Traveller travellerOne = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
                .build();
        Traveller travellerTwo = new Traveller().toBuilder()
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
                .build();
        List<Traveller> list = new ArrayList<>();
        list.add(travellerOne);
        list.add(travellerTwo);
        repository.saveAll(list);
        userOneId = travellerOne.getId();
        userTwoId = travellerTwo.getId();
        LOGGER.info("Getting one user");
        //When
        System.out.println(userOneId);
        TravellerDto user = service.getUser(userOneId);

        //Than
        assertEquals("Joanna",user.getFirstName());
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetTravellerException() throws NotFoundException{
        //Given
        LOGGER.info("Putting two users to database.");
        Traveller travellerOne = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
                .build();
        Traveller travellerTwo = new Traveller().toBuilder()
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
                .build();
        List<Traveller> list = new ArrayList<>();
        list.add(travellerOne);
        list.add(travellerTwo);
        repository.saveAll(list);
        userOneId = travellerOne.getId();
        userTwoId = travellerTwo.getId();
        //When
        TravellerDto user = service.getUser(500000L);
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
    }

    @Test
    public void shouldUpdateTraveller(){
        LOGGER.info("Updating user.");
        //Given
        Trip trip = new Trip().toBuilder()
                .price(1200L)
                .city("Krakow")
                .description("Bad trvvddsvsdvvdvdsvsip")
                .length("5 days")
                .additions(new ArrayList<>())
                .build();
        Long tripId = tripRepository.save(trip).getId();
        trips.add(trip);
        Traveller beforeTraveller = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
                .build();
        repository.save(beforeTraveller);
        Long num = beforeTraveller.getId();
        //When
        beforeTraveller.setFirstName("July");
        beforeTraveller.setLastName("Bard-Dell");
        beforeTraveller.setMail("user@mail.com");
        beforeTraveller.setPassword("password");
        beforeTraveller.setRole(beforeTraveller.getRole());
        beforeTraveller.setAvatarUrl(beforeTraveller.getAvatarUrl());
        beforeTraveller.setTrips(beforeTraveller.getTrips());
        beforeTraveller.setOpinions(beforeTraveller.getOpinions());
        service.updateUser(mapper.toTravellerDto(beforeTraveller));
        //Than
        assertEquals("July",repository.getOne(num).getFirstName());
        assertEquals("Bard-Dell",repository.getOne(num).getLastName());
        assertEquals("user@mail.com",repository.getOne(num).getMail());
        //Cleanup
        repository.deleteById(num);
    }

    @Test
    public void shouldCreateTraveller(){
        LOGGER.info("Creating user");
        //Given
        Traveller traveller = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
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
        repository.deleteById(thirdUserId);
    }

    @Test
    public void shouldDeleteTraveller(){
        LOGGER.info("Deleting all of the users.");
        //Given
        Traveller traveller = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
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


    }

}