package com.kari.travel_agency.mapper;


import com.kari.travel_agency.dto.TripDto;
import com.kari.travel_agency.entity.Trip;
import org.junit.Test;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class TripMapperTest {


    @Test
    public void mapToTripDtoAndBack(){
        //Given
        Trip trip = new Trip("Warsaw", "Stuttgart", 2, 2500L,
                "All inclusive", "Nice", "basic", 5,
                LocalDate.of(2019,10,13), LocalDate.of(2019,10,18),
                "www.google.pl");
        TripMapper mapper = new TripMapper();

        //When
        TripDto tripDto = mapper.toTripDto(trip);
        System.out.println(tripDto);
        Trip newTrip = mapper.toTrip(tripDto);

        //Than
        assertEquals("Warsaw",tripDto.getArrivedAirport());
        assertEquals("basic",newTrip.getStandard());
    }

    @Test
    public void mapToTripDtoList(){
        //Given
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
        TripMapper mapper = new TripMapper();
       //When
        List<TripDto> dtoList = mapper.toTripDtoListWhole(list);

        //Than
        assertEquals("Warsaw",dtoList.get(0).getArrivedAirport());
    }

}