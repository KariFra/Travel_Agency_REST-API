package com.kari.travel_agency.mapper;


import com.kari.travel_agency.dto.TripDto;
import com.kari.travel_agency.entity.Trip;
import com.kari.travel_agency.entity.User;
import com.kari.travel_agency.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TripMapperTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void mapToTripDtoAndBack(){
        //Given
        User user = new User("Joanna", "Dark", "lalalland", "www.ggg.pl");
        userRepository.save(user);
        System.out.println(user.getId());
        Trip trip = new Trip(1L,"Warsaw","Stuttgart", user);

        TripMapper mapper = new TripMapper();
        //When
        TripDto tripDto = mapper.toTripDto(trip);
        System.out.println(tripDto);
//        Trip newTrip = mapper.toTrip(tripDto);

        //Than
        assertEquals("Warsaw",tripDto.getArrivedAirport());
//        assertEquals("Joanna",newTrip.getUser().getFirstName());
    }

}