package com.kari.travelagency.service;

import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.TravellerMapper;
import com.kari.travelagency.repository.TravellerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TravellerService {

    @Autowired
    private TravellerRepository repository;

    @Autowired
    private TravellerMapper travellerMapper;

    public List<TravellerDto> getUsers(){
        return travellerMapper.toUserDtoList(repository.findAll());
    }

    public TravellerDto getUser(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("User with the id number: "+id+" was not found"));
        return travellerMapper.toUserDto(repository.getOne(id));
    }

    public Traveller createNewUser(Traveller traveller){
        List<Trip> trips = new ArrayList<>();
        List<Opinion> opinions = new ArrayList<>();
        Traveller newTraveller = new Traveller().toBuilder()
                .firstName(traveller.getFirstName())
                .lastName(traveller.getLastName())
                .mail(traveller.getMail())
                .password(traveller.getPassword())
                .role(traveller.getRole())
                .avatarUrl(traveller.getAvatarUrl())
                .trips(trips)
                .opinionsGiven(opinions)
                .build();

        return repository.save(newTraveller);
    }

    public void deleteUser(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("User with the id number: "+id+" was not found"));
        repository.deleteById(id);
    }

    public TravellerDto updateUser(TravellerDto travellerDto){
        Traveller newTraveller = repository.getOne(travellerDto.getId());
        newTraveller.setFirstName(travellerDto.getFirstName());
        newTraveller.setLastName(travellerDto.getLastName());
        newTraveller.setMail(travellerDto.getMail());
        newTraveller.setPassword(travellerDto.getPassword());
        return travellerMapper.toUserDto(repository.save(newTraveller));
    }
}
