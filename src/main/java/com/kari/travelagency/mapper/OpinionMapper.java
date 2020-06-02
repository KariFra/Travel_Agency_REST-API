package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.OpinionDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.repository.TravellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OpinionMapper {

    @Autowired
    private TravellerRepository userRepository;


    public Opinion toOpinion(OpinionDto opinionDto){
        Traveller traveller = userRepository.getOne(opinionDto.getUserId());
        return new Opinion(opinionDto.getId(), opinionDto.getMessage(), traveller, opinionDto.getRating());
    }

    public OpinionDto toOpinionDto(Opinion opinion){
        return new OpinionDto(opinion.getId(), opinion.getMessage(), opinion.getTraveller().getId(), opinion.getRating());
    }


    public List<OpinionDto> toOpinionDtoList (List<Opinion> list){
        return list.stream()
                .map(opinion -> toOpinionDto(opinion))
                .collect(Collectors.toList());
    }
}
