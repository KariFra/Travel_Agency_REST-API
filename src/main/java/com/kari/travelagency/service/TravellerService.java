package com.kari.travelagency.service;

import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.TravellerMapper;
import com.kari.travelagency.mapper.TripMapper;
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
    private TripMapper tripMapper;

    @Autowired
    private TravellerMapper travellerMapper;



    public List<TravellerDto> getUsers(){
        return travellerMapper.toTravellerDtoList(repository.findAll());
    }

    public TravellerDto getUser(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("User with the id number: "+id+" was not found"));
        Traveller traveller = repository.getOne(id);
        return travellerMapper.toTravellerDto(repository.getOne(id));
    }

    public Traveller createNewUser(Traveller traveller){
        List<String> opinions = new ArrayList<>();
        Traveller newTraveller = new Traveller().toBuilder()
                .firstName(traveller.getFirstName())
                .lastName(traveller.getLastName())
                .mail(traveller.getMail())
                .password(traveller.getPassword())
                .role(traveller.getRole())
                .avatarUrl(traveller.getAvatarUrl())
                .trips(traveller.getTrips())
                .opinions(opinions)
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
        newTraveller.setOpinions(travellerDto.getOpinions());
        List<Long> longs = travellerDto.getTripsId();
        newTraveller.setTrips(tripMapper.toTripList(longs));
        return travellerMapper.toTravellerDto(repository.save(newTraveller));
    }
}
