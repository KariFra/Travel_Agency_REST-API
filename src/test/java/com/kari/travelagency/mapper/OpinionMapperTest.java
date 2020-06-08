package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.OpinionDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.repository.TravellerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OpinionMapperTest {


    @Autowired
    private OpinionMapper opinionMapper;

    @Test
    public void mapToOpinionDtoAndBack(){
        //Given
        Opinion opinion = new Opinion("This trip was awesome", "traveller",10);

        //When
        OpinionDto opinionDto = opinionMapper.toOpinionDto(opinion);
        Opinion newOpinion = opinionMapper.toOpinion(opinionDto);

        //Then
        assertEquals("This trip was awesome",opinionDto.getMessage());
        assertEquals(10,newOpinion.getRating());
    }

    @Test
    public void mapToOpinionDtoList(){
        //Given

        Opinion opinionOne = new Opinion("This trip was awesome", "traveller", 10);
        Opinion opinionTwo = new Opinion("This trip was so bad I came back home", "traveller", 1);
        List<Opinion> list = new ArrayList<>();
        list.add(opinionOne);
        list.add(opinionTwo);
        //When
        List<OpinionDto> dtoList = opinionMapper.toOpinionDtoList(list);

        //Than
        assertEquals(10,dtoList.get(0).getRating());
    }

}