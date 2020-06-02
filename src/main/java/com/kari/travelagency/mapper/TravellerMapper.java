package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Traveller;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TravellerMapper {


    public Traveller toUser(TravellerDto travellerDto){
        return new Traveller().toBuilder()
                .id(travellerDto.getId())
                .firstName(travellerDto.getFirstName())
                .lastName(travellerDto.getLastName())
                .mail(travellerDto.getMail())
                .password(travellerDto.getPassword())
                .role(travellerDto.getRole())
                .avatarUrl(travellerDto.getAvatarUrl())
                .build();
    }

    public TravellerDto toUserDto(Traveller traveller){
       return new TravellerDto().toBuilder()
                .id(traveller.getId())
                .firstName(traveller.getFirstName())
                .lastName(traveller.getLastName())
                .mail(traveller.getMail())
                .password(traveller.getPassword())
                .role(traveller.getRole())
                .avatarUrl(traveller.getAvatarUrl())
                .build();
    }

    public List<TravellerDto> toUserDtoList(List<Traveller> list){
        return list.stream()
                .map(user -> toUserDto(user))
                .collect(Collectors.toList());
    }
}
