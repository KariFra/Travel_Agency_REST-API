package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.OpinionDto;
import com.kari.travel_agency.entity.Opinion;
import com.kari.travel_agency.entity.User;
import com.kari.travel_agency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpinionMapper {


    @Autowired
    private UserRepository userRepository;

    public Opinion toOpinion(OpinionDto opinionDto){
        return new Opinion(opinionDto.getId(), opinionDto.getMessage(), opinionDto.getRating());
    }

    public OpinionDto toOpinionDto(Opinion opinion){
        return new OpinionDto(opinion.getId(), opinion.getMessage(), opinion.getRating());
    }


    public List<OpinionDto> toOpinionDtoList (List<Opinion> list){
        return list.stream()
                .map(opinion -> toOpinionDto(opinion))
                .collect(Collectors.toList());
    }
}
