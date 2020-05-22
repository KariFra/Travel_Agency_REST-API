package com.kari.travel_agency.service;

import com.kari.travel_agency.dto.OpinionDto;
import com.kari.travel_agency.entity.Opinion;
import com.kari.travel_agency.exception.NotFoundException;
import com.kari.travel_agency.mapper.OpinionMapper;
import com.kari.travel_agency.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionService {

    @Autowired
    private OpinionRepository repository;

    @Autowired
    private OpinionMapper mapper;

    public List<OpinionDto> getOpinions(){
        return mapper.toOpinionDtoList(repository.findAll());
    }

    public OpinionDto getOpinion(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Opinion with the id number: "+id+" was not found"));
        return mapper.toOpinionDto(repository.getOne(id));
    }

    public void createNewOpinion(Opinion opinion){
        Opinion newOpinion = new Opinion(opinion.getMessage(),opinion.getRating());
        repository.save(newOpinion);
    }

    public void deleteOpinion(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Opinion with the id number: "+id+" was not found"));
        repository.deleteById(id);
    }
}
