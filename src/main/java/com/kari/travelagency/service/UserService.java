package com.kari.travelagency.service;

import com.kari.travelagency.dto.UserDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.entity.User;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.UserMapper;
import com.kari.travelagency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> getUsers(){
        return userMapper.toUserDtoList(repository.findAll());
    }

    public UserDto getUser(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("User with the id number: "+id+" was not found"));
        return userMapper.toUserDto(repository.getOne(id));
    }

    public User createNewUser(User user){
        List<Trip> trips = new ArrayList<>();
        List<Opinion> opinions = new ArrayList<>();
        User newUser = new User().toBuilder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail(user.getMail())
                .password(user.getPassword())
                .role(user.getRole())
                .avatarUrl(user.getAvatarUrl())
                .trips(trips)
                .opinionsGiven(opinions)
                .build();

        return repository.save(newUser);
    }

    public void deleteUser(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("User with the id number: "+id+" was not found"));
        repository.deleteById(id);
    }

    public UserDto updateUser(UserDto userDto){
        User newUser = repository.getOne(userDto.getId());
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setMail(userDto.getMail());
        newUser.setPassword(userDto.getPassword());
        return userMapper.toUserDto(repository.save(newUser));
    }
}
