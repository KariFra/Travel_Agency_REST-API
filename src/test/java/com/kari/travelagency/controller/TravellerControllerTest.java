package com.kari.travelagency.controller;

import com.google.gson.Gson;
import com.kari.travelagency.dto.TravellerDto;
import com.kari.travelagency.entity.Traveller;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.TravellerMapper;
import com.kari.travelagency.service.TravellerService;
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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TravellerController.class)
public class TravellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravellerService service;

    @MockBean
    private TravellerMapper mapper;

    @Test
    public void shouldGetUsers() throws Exception{
        //Given
        List<TravellerDto> list = new ArrayList<>();
        list.add(new TravellerDto().toBuilder()
                .id(1L)
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:ball.svg")
                .build());
        //When & Then
        when(service.getUsers()).thenReturn(list);
        mockMvc.perform(get("/v1/travellers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].firstName",is("Marc")))
                .andExpect(jsonPath("$[0].lastName",is("Bell")))
                .andExpect(jsonPath("$[0].mail",is("user@mail.com")))
                .andExpect(jsonPath("$[0].avatarUrl",is("https://avatars.dicebear.com/api/bottts/:ball.svg")));

    }
    @Test
    public void shouldGetUser() throws Exception{
        //Given
        TravellerDto travellerDto = new TravellerDto().toBuilder()
                .id(1L)
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:ball.svg")
                .build();
        //When & Then
        when(service.getUser(1L)).thenReturn(travellerDto);
        mockMvc.perform(get("/v1/travellers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.firstName",is("Marc")))
                .andExpect(jsonPath("$.lastName",is("Bell")))
                .andExpect(jsonPath("$.mail",is("user@mail.com")))
                .andExpect(jsonPath("$.avatarUrl",is("https://avatars.dicebear.com/api/bottts/:ball.svg")));

    }

    @Test
    public void noTrip() throws Exception{
        //When & Then
        when(service.getUser(1L)).thenReturn(null);
        try {
            mockMvc.perform(get("/v1/travellers/1")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().is(200));
        } catch (NestedServletException e){
            assertTrue(e.getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void shouldCreateUser() throws Exception{
        //Given
        Traveller traveller = new Traveller().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        TravellerDto travellerDto = new TravellerDto().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(travellerDto);
        //When & Then
        when(mapper.toTraveller(any(TravellerDto.class))).thenReturn(traveller);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/travellers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));

        Mockito.verify(service).createNewUser(traveller);

    }

    @Test
    public void shouldUpdateUser() throws Exception{
        //Given
        TravellerDto travellerDto = new TravellerDto().toBuilder()
                .id(1L)
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:ball.svg")
                .build();

        //When & Then
        when(service.updateUser(any(TravellerDto.class))).thenReturn(travellerDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(travellerDto);
        mockMvc.perform(put("/v1/travellers").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.firstName",is("Marc")))
                .andExpect(jsonPath("$.lastName",is("Bell")))
                .andExpect(jsonPath("$.mail",is("user@mail.com")))
                .andExpect(jsonPath("$.avatarUrl",is("https://avatars.dicebear.com/api/bottts/:ball.svg")));

    }

    @Test
    public void shouldDeleteUser() throws Exception{
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/travellers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));


        Mockito.verify(service).deleteUser(1L);
    }

}