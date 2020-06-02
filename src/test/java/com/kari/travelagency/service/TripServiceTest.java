package com.kari.travelagency.service;

import com.kari.travelagency.dto.TripDto;

import com.kari.travelagency.entity.Trip;


import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.TripMapper;
import com.kari.travelagency.repository.OpinionRepository;
import com.kari.travelagency.repository.TripRepository;
import com.kari.travelagency.repository.TravellerRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TripServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TripServiceTest.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TravellerRepository userRepository;

    @Autowired
    private OpinionRepository opinionRepository;

    @BeforeClass
    public static void prep(){
        LOGGER.info("Opening the database");
    }

    @Test
    public void shouldGetTrips(){
        //Given
        LOGGER.info("Getting all trips");

        Trip trip = new Trip().toBuilder()
                .price(1200L)
                .city("Krakow")
                .description("Bad trvvddsvsdvvdvdsvsip")
                .length("5 days")
                .additions(new ArrayList<>())
                .build();
        Trip tripTwo = new Trip().toBuilder()
                .price(1200L)
                .city("Krakow")
                .description("Nicesdvsdvsvsdvsdv trip")
                .length("5 days")
                .additions(new ArrayList<>())
                .build();

        tripRepository.save(trip);
        Long tripId = tripRepository.save(trip).getId();
        tripRepository.save(tripTwo);
        Long tripTwoId = tripRepository.save(tripTwo).getId();
        //When
        List<TripDto> list = tripService.getTrips();
        //Than
        assertTrue(list.size()>=1);
        //Clean
        tripRepository.deleteById(tripId);
        tripRepository.deleteById(tripTwoId);

    }

    @Test
    public void shouldGetTrip(){
        LOGGER.info("Getting one trip");
        //Given
        Trip trip = new Trip().toBuilder()
                .price(1200L)
                .city("Krakow")
                .description("Nice tridvsdvdsvsdvsdvp")
                .length("5 days")
                .additions(new ArrayList<>())
                .build();
        Trip tripTwo = new Trip().toBuilder()
                .price(1200L)
                .city("Krakow")
                .description("Nice trsdvsdvsdvsdvsdvip")
                .length("5 days")
                .additions(new ArrayList<>())
                .build();

        tripRepository.save(trip);
        Long id = tripRepository.save(trip).getId();
        tripRepository.save(tripTwo);
        Long tripTwoId = tripRepository.save(tripTwo).getId();
        //When
        Trip receivedTrip = tripMapper.toTrip(tripService.getTrip(id));
        //Than
        assertEquals("Nice tridvsdvdsvsdvsdvp",receivedTrip.getDescription());
        //Clean
        tripRepository.deleteById(id);
        tripRepository.deleteById(tripTwoId);

    }

    @Test(expected = NotFoundException.class)
    public void shouldGetTripException() throws NotFoundException{
        //When
        TripDto tripDto = tripService.getTrip(500000L);
    }

    @Test
    public void shouldCreateTrip(){
        LOGGER.info("Creating trip");
        //Given
        Trip trip = new Trip().toBuilder()
                .price(1200L)
                .description("Awful trsdvsdvdsvsdvsdvdsvsdvsip")
                .length("5 days")
                .build();
        //When
        List<Trip> listOne = tripRepository.findAll();
        tripService.createNewTrip(trip);
        List<Trip> listTwo = tripRepository.findAll();
        //Than
        assertTrue(listTwo.size() - listOne.size() == 1);
        //Clean
        tripRepository.deleteAll();
    }


    @Test
    public void shouldUpdateTrip(){
        LOGGER.info("Updating trip");
        //Given
        Traveller newTraveller = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        userRepository.save(newTraveller);
        Long userId = userRepository.save(newTraveller).getId();


        Trip trip = new Trip().toBuilder()
                .price(1200L)
                .city("Gdansk")
                .description("Awful trsdvdsvsdvsdvsdvsdvsdvsip")
                .length("5 days")
                .additions(new ArrayList<>())
                .build();
        tripRepository.save(trip);
        Long id = tripRepository.save(trip).getId();
        Trip newTrip = tripRepository.getOne(id);
        TripDto tripDto = tripMapper.toTripDto(newTrip);
        List<String> list = new ArrayList<>();
        list.add("blablab");
        //When
        tripDto.setUserId(userId);
        tripDto.setAdditions(list);
        tripService.updateTrip(tripDto);
        //Than
        assertEquals("blablab",tripRepository.getOne(id).getAdditions().get(0));
        //Clean
        tripRepository.deleteById(id);

    }

    @Test
    public void shouldDeleteUser(){
        LOGGER.info("Deleting all of the trips.");
        //Given
        Trip trip = new Trip().toBuilder()
                .price(1200L)
                .city("Gdansk")
                .description("Awful tsdvsdvdsvsdvsdvsdvsdvsdvsdrip")
                .length("5 days")
                .additions(new ArrayList<>())
                .build();
        tripRepository.save(trip);
        Long id = tripRepository.save(trip).getId();
        //When
        List<Trip> listOne = tripRepository.findAll();
        tripService.deleteTrip(id);
        List<Trip> listTwo = tripRepository.findAll();
        //Than
        assertTrue(listOne.size() - listTwo.size() == 1);
        //Clean
        tripRepository.deleteAll();

    }
}