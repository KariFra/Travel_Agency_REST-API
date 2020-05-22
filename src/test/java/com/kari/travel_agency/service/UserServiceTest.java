package com.kari.travel_agency.service;

import com.kari.travel_agency.dto.UserDto;
import com.kari.travel_agency.entity.User;
import com.kari.travel_agency.mapper.UserMapper;
import com.kari.travel_agency.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    @Before
    public void prep(){
        LOGGER.info("Putting two users to database.");
        User userOne = new User("Joanna", "Mroz", "ul. Kwiatowa 15/8, Warszawa",
                "https://avatars.dicebear.com/api/bottts/:tree.svg");
        User userTwo = new User("Greg", "Dell", "ul. Kwiatowa 15/8, Kraków",
                "https://avatars.dicebear.com/api/bottts/:ball.svg");
        List<User> list = new ArrayList<>();
        list.add(userOne);
        list.add(userTwo);
        repository.saveAll(list);
    }

    @Test
    public void shouldGetUsers(){
        LOGGER.info("Getting users.");
        //When
        List<UserDto> userList = service.getUsers();
        //Than
        assertTrue(userList.size()!=0);
        assertEquals("Mroz",userList.get(1).getLastName());
    }

    @Test
    public void shouldGetUser(){
        LOGGER.info("Getting one user");
        //When
        List<User> list = repository.findAll();
        Long idNum = list.get(0).getId();
        UserDto user = service.getUser(idNum);

        //Than
        assertEquals("Joanna",user.getFirstName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldGetUserException() throws EntityNotFoundException{
        //When
        UserDto user = service.getUser(50L);
    }

    @Test
    public void shouldUpdateUser(){
        LOGGER.info("Updating user.");
        //Given
        User beforeUser = new User("Kate", "Bard", "ul. Kwiatowa 15/8, Warszawa",
                "https://avatars.dicebear.com/api/bottts/:tree.svg");
        repository.save(beforeUser);
        Long num = beforeUser.getId();
        //When
        beforeUser.setFirstName("July");
        beforeUser.setLastName("Bard-Dell");
        beforeUser.setAddress("lalalaLand 15");
        service.updateUser(mapper.toUserDto(beforeUser));
        //Than
        assertEquals("July",repository.getOne(num).getFirstName());
        assertEquals("Bard-Dell",repository.getOne(num).getLastName());
        assertEquals("lalalaLand 15",repository.getOne(num).getAddress());
    }

    @Test
    public void shouldDeleteUser(){
        LOGGER.info("Deleting all of the users.");
        //Given
        List<User> emptyList = new ArrayList<>();
        //When
        List<User> list = repository.findAll();
        for(int i=0; i<list.size();i++) {
            Long num = list.get(i).getId();
            service.deleteUser(num);
        }
        //Than
        assertTrue(repository.findAll().equals(emptyList));
    }

}