package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.UserDto;
import com.kari.travelagency.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {


    public User toUser(UserDto userDto){
        return new User().toBuilder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .mail(userDto.getMail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .avatarUrl(userDto.getAvatarUrl())
                .build();
    }

    public UserDto toUserDto(User user){
       return new UserDto().toBuilder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail(user.getMail())
                .password(user.getPassword())
                .role(user.getRole())
                .avatarUrl(user.getAvatarUrl())
                .build();
    }

    public List<UserDto> toUserDtoList(List<User> list){
        return list.stream()
                .map(user -> toUserDto(user))
                .collect(Collectors.toList());
    }
}
