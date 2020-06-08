package com.kari.travelagency.mapper;


import com.kari.travelagency.dto.TripDto;
import com.kari.travelagency.entity.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TripMapperTest {


    @Test
    public void mapToTripDtoAndBack(){
        //Given
              Trip trip = new Trip().toBuilder()
                .price(1200L)
                .description("Nice trip")
                .length("5 days")
                .build();
        TripMapper mapper = new TripMapper();


        //When
        TripDto tripDto = mapper.toTripDto(trip);
        Trip newTrip = mapper.toTrip(tripDto);

        //Than
        assertEquals("Nice trip",tripDto.getDescription());
        assertEquals("5 days",newTrip.getLength());
    }

    @Test
    public void mapToTripDtoList(){
        //Given

        Trip tripOne = new Trip().toBuilder()
                .price(1200L)
                .description("Nice trip")
                .length("5 days")
                .build();
        Trip tripTwo = new Trip().toBuilder()
                .price(1200L)
                .description("Awful trip")
                .length("5 days")
                .build();
        List<Trip> list = new ArrayList<>();
        list.add(tripOne);
        list.add(tripTwo);
        TripMapper mapper = new TripMapper();
       //When
        List<TripDto> dtoList = mapper.toTripDtoListWhole(list);

        //Than
        assertEquals("Nice trip",dtoList.get(0).getDescription());
    }

}