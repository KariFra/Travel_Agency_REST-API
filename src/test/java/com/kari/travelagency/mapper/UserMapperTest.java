package com.kari.travelagency.mapper;



import com.kari.travelagency.dto.UserDto;
import com.kari.travelagency.entity.User;
import org.junit.Test;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserMapperTest {

    @Test
    public void mapToUserDtoAndBack(){
        //Given
        User user = new User().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();

        UserMapper mapper = new UserMapper();

        //When
        UserDto userDto = mapper.toUserDto(user);
        User newUser = mapper.toUser(userDto);

        //Than
        assertEquals("user@mail.com",userDto.getMail());
        assertEquals("Mroz",newUser.getLastName());
    }

    @Test
    public void mapToUserDtoList(){
        //Given
        User user = new User().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
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