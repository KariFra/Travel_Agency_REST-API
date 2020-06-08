package com.kari.travelagency.mapper;



import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.repository.TripRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TravellerMapperTest {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TravellerMapper mapper;

    @Test
    public void mapToTravellerDtoAndBack(){
        //Given
        List<Trip> trips = new ArrayList<>();
        Trip trip = new Trip().toBuilder()
                .price(1200L)
                .city("Krakow")
                .description("Bad trvvddsvsdvvdvdsvsip")
                .length("5 days")
                .additions(new ArrayList<>())
                .build();
        Long id = tripRepository.save(trip).getId();
        trips.add(trip);
        List<String> opinions = new ArrayList<>();
        Traveller traveller = new Traveller().toBuilder()
                .id(1L)
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
                .build();



        //When
        TravellerDto travellerDto = mapper.toTravellerDto(traveller);
        Traveller newTraveller = mapper.toTraveller(travellerDto);

        //Than
        assertEquals("user@mail.com", travellerDto.getMail());
        assertEquals("Mroz", newTraveller.getLastName());

        //Clear
        tripRepository.deleteById(id);
    }

    @Test
    public void mapToTravellerDtoList(){
        //Given
        List<Trip> trips = new ArrayList<>();
        Trip trip = new Trip().toBuilder()
                .price(1200L)
                .city("Krakow")
                .description("Bad trvvddsvsdvvdvdsvsip")
                .length("5 days")
                .additions(new ArrayList<>())
                .build();
        Long id = tripRepository.save(trip).getId();
        trips.add(trip);
        List<String> opinions = new ArrayList<>();
        Traveller traveller = new Traveller().toBuilder()
                .id(1L)
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .password("password")
                .role("USER")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .trips(trips)
                .opinions(opinions)
                .build();
        List<Traveller> list = new ArrayList<>();
        list.add(traveller);

        //When
        List<TravellerDto> listDto = mapper.toTravellerDtoList(list);

        //Than
        assertEquals(1,listDto.size());
        assertEquals("Joanna",listDto.get(0).getFirstName());

        //Clear
        tripRepository.deleteById(id);


    }

}