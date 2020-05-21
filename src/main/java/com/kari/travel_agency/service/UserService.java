package com.kari.travel_agency.service;

import com.kari.travel_agency.dto.UserDto;
import com.kari.travel_agency.entity.Opinion;
import com.kari.travel_agency.entity.Trip;
import com.kari.travel_agency.entity.User;
import com.kari.travel_agency.exception.NotFoundException;
import com.kari.travel_agency.mapper.UserMapper;
import com.kari.travel_agency.repository.UserRepository;
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
        repository.findById(id).orElseThrow(() -> new NotFoundException("User with the id number:"+id+" was not found"));
            return userMapper.toUserDto(repository.getOne(id));

    }

    public void createNewUser(User user){
        ArrayList<Trip> trips = new ArrayList<>();
        ArrayList<Opinion> opinions = new ArrayList<>();
        User newUser = new User(user.getId(),user.getFirstName(),user.getLastName(), user.getAddress(), user.getAvatarUrl(),
                trips, opinions);
        repository.save(newUser);
    }

    public void deleteUser(Long id){
        repository.findById(id).orElseThrow(() -> new NotFoundException("User with the id number:"+id+" was not found"));
        repository.deleteById(id);
    }

    public UserDto updateUser(UserDto userDto){
        User newUser = repository.getOne(userDto.getId());
        newUser.setFirstName(userDto.getFirstName());
        newUser.setLastName(userDto.getLastName());
        newUser.setAddress(userDto.getAddress());
        repository.save(newUser);
        return userMapper.toUserDto(repository.getOne(newUser.getId()));
    }
}
