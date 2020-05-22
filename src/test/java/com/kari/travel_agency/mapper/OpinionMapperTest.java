package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.OpinionDto;
import com.kari.travel_agency.entity.Opinion;
import com.kari.travel_agency.entity.User;
import com.kari.travel_agency.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private OpinionMapper opinionMapper;

    @Test
    public void mapToOpinionDtoAndBack(){
        //Given
        User user = new User("Valentina","Tralala","Helms Deep","www.google.pl");
        userRepository.save(user);
        Opinion opinion = new Opinion(1L,"This trip was awesome",user,10);

        //When
        OpinionDto opinionDto = opinionMapper.toOpinionDto(opinion);
        Opinion newOpinion = opinionMapper.toOpinion(opinionDto);

        //Then
        assertEquals("This trip was awesome",opinionDto.getMessage());
        assertEquals("Valentina",newOpinion.getUser().getFirstName());
    }

    @Test
    public void mapToOpinionDtoList(){
        //Given
        User user = new User("Valentina","Tralala","Helms Deep","www.google.pl");
        Opinion opinionOne = new Opinion(1L,"This trip was awesome",user,10);
        Opinion opinionTwo = new Opinion(2L,"This trip was so bad I came back home",user,1);
        List<Opinion> list = new ArrayList<>();
        list.add(opinionOne);
        list.add(opinionTwo);
        //When
        List<OpinionDto> dtoList = opinionMapper.toOpinionDtoList(list);

        //Than
        assertEquals(10,dtoList.get(0).getRating());
    }

}