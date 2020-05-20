package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.OpinionDto;
import com.kari.travel_agency.entity.Opinion;
import com.kari.travel_agency.repository.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpinionMapper {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OpinionRepository repository;

    public Opinion toOpinion(OpinionDto opinionDto){
        return new Opinion(opinionDto.getId(), opinionDto.getMessage(),userMapper.toUser(opinionDto.getUser()) ,
                opinionDto.getRating());
    }

    public OpinionDto toOpinionDto(Opinion opinion){
        return new OpinionDto(opinion.getId(), opinion.getMessage(),userMapper.toUserDto(opinion.getUser()) ,
                opinion.getRating());
    }

    public List<Opinion> toOpinionList (List<Long> list){
        return list.stream()
                .map(element -> repository.getOne(element))
                .collect(Collectors.toList());
    }

    public List<Long> toOpinionDtoList (List<Opinion> list){
        return list.stream()
                .map(opinion -> opinion.getId())
                .collect(Collectors.toList());
    }
}
