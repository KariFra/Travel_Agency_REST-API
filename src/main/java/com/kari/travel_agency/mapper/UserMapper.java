package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.UserDto;
import com.kari.travel_agency.entity.User;
import com.kari.travel_agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private TripMapper tripMapper;

    @Autowired
    private OpinionMapper opinionMapper;

    @Autowired
    private UserRepository repository;


    public User toUser(UserDto userDto){
        return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getAddress(),
                userDto.getAvatarUrl());
    }

    public  UserDto toUserDto(User user){
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getAddress(), user.getAvatarUrl());
    }

    public List<User> toUserList(List<Long> list){
        return list.stream()
                .map(id -> repository.getOne(id))
                .collect(Collectors.toList());
    }

    public List<Long> toUserIdList(List<User> list){
        return list.stream()
                .map(user -> user.getId())
                .collect(Collectors.toList());
    }

    public List<UserDto> toUserDtoList(List<User> list){
        return list.stream()
                .map(user -> toUserDto(user))
                .collect(Collectors.toList());
    }
}
