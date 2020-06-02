package com.kari.travelagency.service;

import com.kari.travelagency.dto.UserDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.entity.UserEntity;
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

    public UserEntity createNewUser(UserEntity userEntity){
        List<Trip> trips = new ArrayList<>();
        List<Opinion> opinions = new ArrayList<>();
        UserEntity newUserEntity = new UserEntity().toBuilder()
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .mail(userEntity.getMail())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .avatarUrl(userEntity.getAvatarUrl())
                .trips(trips)
                .opinionsGiven(opinions)
                .build();

        return repository.save(newUserEntity);
    }

    public void deleteUser(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("User with the id number: "+id+" was not found"));
        repository.deleteById(id);
    }

    public UserDto updateUser(UserDto userDto){
        UserEntity newUserEntity = repository.getOne(userDto.getId());
        newUserEntity.setFirstName(userDto.getFirstName());
        newUserEntity.setLastName(userDto.getLastName());
        newUserEntity.setMail(userDto.getMail());
        newUserEntity.setPassword(userDto.getPassword());
        return userMapper.toUserDto(repository.save(newUserEntity));
    }
}
