package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.OpinionDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.entity.UserEntity;
import com.kari.travelagency.repository.UserRepository;
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
        UserEntity userEntity = new UserEntity().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user2@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();

        userRepository.save(userEntity);
        Opinion opinion = new Opinion("This trip was awesome", userEntity,10);

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
        UserEntity userEntity = new UserEntity().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user2@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        userRepository.save(userEntity);
        Opinion opinionOne = new Opinion("This trip was awesome", userEntity, 10);
        Opinion opinionTwo = new Opinion("This trip was so bad I came back home", userEntity, 1);
        List<Opinion> list = new ArrayList<>();
        list.add(opinionOne);
        list.add(opinionTwo);
        //When
        List<OpinionDto> dtoList = opinionMapper.toOpinionDtoList(list);

        //Than
        assertEquals(10,dtoList.get(0).getRating());
    }

}