package com.kari.travelagency.controller;


import com.google.gson.Gson;
import com.kari.travelagency.dto.OpinionDto;
import com.kari.travelagency.entity.Opinion;
import com.kari.travelagency.entity.UserEntity;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.OpinionMapper;
import com.kari.travelagency.service.OpinionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;



import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OpinionController.class)
public class OpinionControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OpinionService service;

    @MockBean
    private OpinionMapper mapper;

    @Test
    public void shouldGetOpinions() throws Exception{
        //Given
        List<OpinionDto> list = new ArrayList<>();
        list.add(new OpinionDto(1L,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 1L, 10));

        //When & Than
        when(service.getOpinions()).thenReturn(list);
        mockMvc.perform(get("/v1/opinions").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].message",is("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")))
                .andExpect(jsonPath("$[0].userId",is(1)))
                .andExpect(jsonPath("$[0].rating",is(10)));


    }

    @Test
    public void shouldGetOpinion() throws Exception{
        //Given

        OpinionDto opinionDto = new OpinionDto(1L,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 1L, 10);

        //When & Than
        when(service.getOpinion(1L)).thenReturn(opinionDto);
        mockMvc.perform(get("/v1/opinions/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.message",is("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")))
                .andExpect(jsonPath("$.userId",is(1)))
                .andExpect(jsonPath("$.rating",is(10)));
    }

    @Test
    public void noOpinion() throws Exception {
        //Given
        when(service.getOpinion(1L)).thenReturn(null);

        //When & Than
        try {
            mockMvc.perform(get("/v1/opinions/1")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().is(200));
        } catch (NestedServletException e){
            assertTrue(e.getCause() instanceof NotFoundException);
        }

    }

    @Test
    public void shouldCreateOpinion() throws Exception{
        //Given

        UserEntity userEntity = new UserEntity().toBuilder()
                .id(1L)
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        OpinionDto opinionDto = new OpinionDto(1L,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", 1L, 10);
        Opinion opinion = new Opinion(1L,"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", userEntity, 10 );

        //When & Than
        when(mapper.toOpinion(any(OpinionDto.class))).thenReturn(opinion);
        when(service.createNewOpinion(opinion)).thenReturn(opinion);
        when(mapper.toOpinionDto(any(Opinion.class))).thenReturn(opinionDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(opinionDto);


        mockMvc.perform(post("/v1/opinions").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.message",is("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")))
                .andExpect(jsonPath("$.userId",is(1)))
                .andExpect(jsonPath("$.rating",is(10)));

    }

    @Test
    public void shouldDeleteOpinion() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/opinions/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));


        Mockito.verify(service).deleteOpinion(1L);
    }

}