package com.kari.travelagency.service;

import com.kari.travelagency.dto.OpinionDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.OpinionMapper;
import com.kari.travelagency.repository.OpinionRepository;
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

    public Opinion createNewOpinion(Opinion opinion){
        Opinion newOpinion = new Opinion(opinion.getMessage(), opinion.getTraveller(), opinion.getRating());
        return repository.save(newOpinion);
    }

    public void deleteOpinion(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Opinion with the id number: "+id+" was not found"));
        repository.deleteById(id);
    }
}
