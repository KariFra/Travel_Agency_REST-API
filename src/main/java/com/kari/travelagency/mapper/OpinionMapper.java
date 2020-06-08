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

    public Opinion toOpinion(OpinionDto opinionDto){
        return new Opinion(opinionDto.getId(), opinionDto.getMessage(), opinionDto.getUserUrl(), opinionDto.getRating());
    }

    public OpinionDto toOpinionDto(Opinion opinion){
        return new OpinionDto(opinion.getId(), opinion.getMessage(), opinion.getTravellerUrl(), opinion.getRating());
    }


    public List<OpinionDto> toOpinionDtoList (List<Opinion> list){
        return list.stream()
                .map(opinion -> toOpinionDto(opinion))
                .collect(Collectors.toList());
    }
}
