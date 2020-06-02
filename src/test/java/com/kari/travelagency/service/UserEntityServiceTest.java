package com.kari.travelagency.service;

import com.kari.travelagency.dto.UserDto;
import com.kari.travelagency.entity.UserEntity;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.UserMapper;
import com.kari.travelagency.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserEntityServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntityServiceTest.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

    @Autowired
    private UserMapper mapper;

    private Long userOneId = 0L;
    private Long userTwoId = 0L;

    @Before
    public void prep(){
        LOGGER.info("Putting two users to database.");
        UserEntity userEntityOne = new UserEntity().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        UserEntity userEntityTwo = new UserEntity().toBuilder()
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:ball.svg")
                .build();
        List<UserEntity> list = new ArrayList<>();
        list.add(userEntityOne);
        list.add(userEntityTwo);
        repository.saveAll(list);
        userOneId = userEntityOne.getId();
        userTwoId = userEntityTwo.getId();
    }

    @Test
    public void shouldGetUsers(){
        LOGGER.info("Getting users.");
        //When
        List<UserDto> userList = service.getUsers();
        System.out.println(userList);
        //Than
        assertTrue(userList.size()!=0);
        assertEquals("Mroz",userList.get(1).getLastName());
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
    }

    @Test
    public void shouldGetUser(){
        LOGGER.info("Getting one user");
        //When
        List<UserEntity> list = repository.findAll();
        UserDto user = service.getUser(userOneId);

        //Than
        assertEquals("Joanna",user.getFirstName());
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetUserException() throws NotFoundException{
        //When
        UserDto user = service.getUser(500000L);
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
    }

    @Test
    public void shouldUpdateUser(){
        LOGGER.info("Updating user.");
        //Given
        UserEntity beforeUserEntity = new UserEntity().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        repository.save(beforeUserEntity);
        Long num = beforeUserEntity.getId();
        //When
        beforeUserEntity.setFirstName("July");
        beforeUserEntity.setLastName("Bard-Dell");
        beforeUserEntity.setMail("user@mail.com");
        service.updateUser(mapper.toUserDto(beforeUserEntity));
        //Than
        assertEquals("July",repository.getOne(num).getFirstName());
        assertEquals("Bard-Dell",repository.getOne(num).getLastName());
        assertEquals("user@mail.com",repository.getOne(num).getMail());
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
        repository.deleteById(num);
    }

    @Test
    public void shouldCreateUser(){
        LOGGER.info("Creating user");
        //Given
        UserEntity userEntity = new UserEntity().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        List<UserEntity> listOne = repository.findAll();
        int sizeOne = listOne.size();
        Long thirdUserId = service.createNewUser(userEntity).getId();
        //When
        List<UserEntity> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();

        //Than
        assertTrue(sizeTwo - sizeOne == 1);
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);
        repository.deleteById(thirdUserId);
    }

    @Test
    public void shouldDeleteUser(){
        LOGGER.info("Deleting all of the users.");
        //Given
        UserEntity userEntity = new UserEntity().toBuilder()
                .firstName("Dereck")
                .lastName("Mroz")
                .mail("user2@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        Long userId = repository.save(userEntity).getId();
        List<UserEntity> listOne = repository.findAll();
        //When
        int sizeOne = listOne.size();
        service.deleteUser(userId);
        List<UserEntity> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();
        //Than
        assertTrue(sizeOne - sizeTwo == 1);
        //Cleanup
        repository.deleteById(userOneId);
        repository.deleteById(userTwoId);

    }

}