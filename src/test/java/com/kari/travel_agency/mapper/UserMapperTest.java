package com.kari.travel_agency.mapper;



import com.kari.travel_agency.dto.UserDto;
import com.kari.travel_agency.entity.User;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserMapperTest {

    @Test
    public void mapToUserDtoAndBack(){
        //Given
        User user = new User("Joanna", "Mroz", "ul. Kwiatowa 15/8, Warszawa",
                "https://avatars.dicebear.com/api/bottts/:tree.svg");

        UserMapper mapper = new UserMapper();

        //When
        UserDto userDto = mapper.toUserDto(user);
        User newUser = mapper.toUser(userDto);

        //Than
        assertEquals("ul. Kwiatowa 15/8, Warszawa",userDto.getAddress());
        assertEquals("Mroz",newUser.getLastName());
    }

    @Test
    public void mapToUserDtoList(){
        //Given
        User user = new User("Joanna", "Mroz", "ul. Kwiatowa 15/8, Warszawa",
                "https://avatars.dicebear.com/api/bottts/:tree.svg");
        List<User> list = new ArrayList<>();
        list.add(user);
        UserMapper mapper = new UserMapper();

        //When
        List<UserDto> listDto = mapper.toUserDtoList(list);

        //Than
        assertEquals(1,listDto.size());
        assertEquals("Joanna",listDto.get(0).getFirstName());



    }

}