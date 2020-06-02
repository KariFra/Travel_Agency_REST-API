package com.kari.travelagency.mapper;



import com.kari.travelagency.dto.UserDto;
import com.kari.travelagency.entity.UserEntity;
import org.junit.Test;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserEntityMapperTest {

    @Test
    public void mapToUserDtoAndBack(){
        //Given
        UserEntity userEntity = new UserEntity().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();

        UserMapper mapper = new UserMapper();

        //When
        UserDto userDto = mapper.toUserDto(userEntity);
        UserEntity newUserEntity = mapper.toUser(userDto);

        //Than
        assertEquals("user@mail.com",userDto.getMail());
        assertEquals("Mroz", newUserEntity.getLastName());
    }

    @Test
    public void mapToUserDtoList(){
        //Given
        UserEntity userEntity = new UserEntity().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        List<UserEntity> list = new ArrayList<>();
        list.add(userEntity);
        UserMapper mapper = new UserMapper();

        //When
        List<UserDto> listDto = mapper.toUserDtoList(list);

        //Than
        assertEquals(1,listDto.size());
        assertEquals("Joanna",listDto.get(0).getFirstName());



    }

}