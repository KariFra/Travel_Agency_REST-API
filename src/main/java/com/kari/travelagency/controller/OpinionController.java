package com.kari.travelagency.controller;

import com.kari.travelagency.dto.OpinionDto;
import com.kari.travelagency.mapper.OpinionMapper;
import com.kari.travelagency.service.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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

    @GetMapping("/{id}")
    public OpinionDto getOpinion(@PathVariable Long id){
        return service.getOpinion(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public OpinionDto createOpinion(@RequestBody OpinionDto opinionDto){
        return mapper.toOpinionDto(service.createNewOpinion(mapper.toOpinion(opinionDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteOpinion(@PathVariable Long id) {service.deleteOpinion(id);}

}
