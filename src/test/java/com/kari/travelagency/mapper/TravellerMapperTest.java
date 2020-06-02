package com.kari.travelagency.mapper;



import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Traveller;
import org.junit.Test;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TravellerMapperTest {

    @Test
    public void mapToUserDtoAndBack(){
        //Given
        Traveller traveller = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();

        TravellerMapper mapper = new TravellerMapper();

        //When
        TravellerDto travellerDto = mapper.toUserDto(traveller);
        Traveller newTraveller = mapper.toUser(travellerDto);

        //Than
        assertEquals("user@mail.com", travellerDto.getMail());
        assertEquals("Mroz", newTraveller.getLastName());
    }

    @Test
    public void mapToUserDtoList(){
        //Given
        Traveller traveller = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        List<Traveller> list = new ArrayList<>();
        list.add(traveller);
        TravellerMapper mapper = new TravellerMapper();

        //When
        List<TravellerDto> listDto = mapper.toUserDtoList(list);

        //Than
        assertEquals(1,listDto.size());
        assertEquals("Joanna",listDto.get(0).getFirstName());



    }

}