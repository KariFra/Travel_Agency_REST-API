package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.UserDto;
import com.kari.travelagency.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {


    public UserEntity toUser(UserDto userDto){
        return new UserEntity().toBuilder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .mail(userDto.getMail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .avatarUrl(userDto.getAvatarUrl())
                .build();
    }

    public UserDto toUserDto(UserEntity userEntity){
       return new UserDto().toBuilder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .mail(userEntity.getMail())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .avatarUrl(userEntity.getAvatarUrl())
                .build();
    }

    public List<UserDto> toUserDtoList(List<UserEntity> list){
        return list.stream()
                .map(user -> toUserDto(user))
                .collect(Collectors.toList());
    }
}
