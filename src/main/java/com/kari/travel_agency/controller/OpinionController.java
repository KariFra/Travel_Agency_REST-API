package com.kari.travel_agency.controller;

import com.kari.travel_agency.dto.OpinionDto;
import com.kari.travel_agency.mapper.OpinionMapper;
import com.kari.travel_agency.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/opinions")
public class OpinionController {

    @Autowired
    private OpinionService service;

    @Autowired
    private OpinionMapper mapper;

    @GetMapping
    public List<OpinionDto> getOpinions(){
        return service.getOpinions();
    }

    @GetMapping(path = "/{id}")
    public OpinionDto getOpinion(@PathVariable Long id){
        return service.getOpinion(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createOpinion(@RequestBody OpinionDto opinionDto){
        service.createNewOpinion(mapper.toOpinion(opinionDto));
    }

}
