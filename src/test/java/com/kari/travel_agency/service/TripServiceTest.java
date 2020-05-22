package com.kari.travel_agency.service;

import com.kari.travel_agency.dto.TripDto;
import com.kari.travel_agency.entity.Trip;
import com.kari.travel_agency.exception.NotFoundException;
import com.kari.travel_agency.mapper.TripMapper;
import com.kari.travel_agency.repository.TripRepository;
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
public class TripServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TripServiceTest.class);

    @Autowired
    private TripService tripService;

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private TripRepository tripRepository;

    private Long tripOneId = 0L;

    @Before
    public void prep(){
        LOGGER.info("Putting two trips into database.");
        Trip tripOne = new Trip("Warsaw", "Stuttgart", 2, 2500L,
                "All inclusive", "Nice", "basic", 5,
                LocalDate.of(2019,10,13), LocalDate.of(2019,10,18),
                "www.google.pl");
        Trip tripTwo = new Trip("Krakow", "Gdansk", 4, 4500L,
                "All inclusive", "Nice", "vip", 5,
                LocalDate.of(2019,10,13), LocalDate.of(2019,10,18),
                "www.google.pl");

        List<Trip> list = new ArrayList<>();
        list.add(tripOne);
        list.add(tripTwo);
        tripRepository.saveAll(list);
        tripOneId = tripOne.getId();
    }

    @Test
    public void shouldGetTrips(){
        LOGGER.info("Getting all trips");
        //When
        List<TripDto> list = tripService.getTrips();
        //Than
        assertEquals("Warsaw",list.get(1).getArrivedAirport());
    }

    @Test
    public void shouldGetTrip(){
        LOGGER.info("Getting one trip");
        //When
        Trip trip = tripMapper.toTrip(tripService.getTrip(tripOneId));
        //Than
        assertEquals("Warsaw",trip.getArrivedAirport());
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetTripException() throws NotFoundException{
        //When
        TripDto tripDto = tripService.getTrip(50L);
    }

    @Test
    public void shouldCreateTrip(){
        LOGGER.info("Creating trip");
        //Given
        Trip trip = new Trip("Miami", "Barcelona", 4, 4500L,
                "All inclusive", "Nice", "vip", 5,
                LocalDate.of(2020,10,13), LocalDate.of(2020,10,18),
                "www.google.pl");
        List<Trip> listOne = tripRepository.findAll();
        int sizeOne = listOne.size();
        tripService.createNewTrip(trip);
        //When
        List<Trip> listTwo = tripRepository.findAll();
        int sizeTwo = listTwo.size();

        //Than
        assertTrue(sizeTwo - sizeOne == 1);
    }

    @Test
    public void shouldUpdateTrip(){
        LOGGER.info("Updating trip");
        Trip trip = tripRepository.getOne(tripOneId);
        //When
        trip.setStandard("mix");
        trip.setDescription("Very boring place");
        trip.setComplaint(true);
        tripService.updateTrip(tripMapper.toTripDto(trip));
        //Than
        assertEquals("Very boring place",tripRepository.getOne(tripOneId).getDescription());
    }

    @Test
    public void shouldDeleteUser(){
        LOGGER.info("Deleting all of the trips.");
        //Given
        List<Trip> emptyList = new ArrayList<>();
        //When
        List<Trip> list = tripRepository.findAll();
        for(int i=0; i<list.size();i++) {
            Long num = list.get(i).getId();
            tripService.deleteTrip(num);
        }
        //Than
        assertTrue(tripRepository.findAll().equals(emptyList));
    }
}