package com.kari.travelagency.controller;


import com.google.gson.Gson;
import com.kari.travelagency.dto.TripDto;
import com.kari.travelagency.entity.Trip;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.TripMapper;
import com.kari.travelagency.service.TripService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TripController.class)
public class TripControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService service;

    @MockBean
    private TripMapper mapper;

    @Test
    public void shouldGetTrips() throws Exception{
        //Given
        List<TripDto> list = new ArrayList<>();
        list.add(new TripDto().toBuilder()
                .id(1L)
                .price(1200L)
                .city("Krakow")
                .description("Nice tridvsdvdsvsdvsdvp")
                .length("5 days")
                .build());
        //When & Then
        when(service.getTrips()).thenReturn(list);
        mockMvc.perform(get("/v1/trips").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].price",is(1200)))
                .andExpect(jsonPath("$[0].city",is("Krakow")))
                .andExpect(jsonPath("$[0].description",is("Nice tridvsdvdsvsdvsdvp")))
                .andExpect(jsonPath("$[0].length",is("5 days")));

    }
    @Test
    public void shouldGetTrip() throws Exception{
        //Given
       TripDto tripDto = new TripDto().toBuilder()
               .id(1L)
               .price(1200L)
               .city("Krakow")
               .description("Nice tridvsdvdsvsdvsdvp")
               .length("5 days")
               .build();
        //When & Then
        when(service.getTrip(1L)).thenReturn(tripDto);
        mockMvc.perform(get("/v1/trips/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.price",is(1200)))
                .andExpect(jsonPath("$.city",is("Krakow")))
                .andExpect(jsonPath("$.description",is("Nice tridvsdvdsvsdvsdvp")))
                .andExpect(jsonPath("$.length",is("5 days")));
    }

    @Test
    public void noTrip() throws Exception{
        //When & Then
        when(service.getTrip(1L)).thenReturn(null);
        try {
            mockMvc.perform(get("/v1/trips/1")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().is(200));
        } catch (NestedServletException e){
            assertTrue(e.getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void shouldCreateTrip() throws Exception{
        //Given
        TripDto tripDto = new TripDto().toBuilder()
                .price(1200L)
                .city("Krakow")
                .description("Nice tridvsdvdsvsdvsdvp")
                .url("https://cdn.pixabay.com/photo/2015/10/23/07/11/bieszczady-1002425_1280.jpg")
                .length("5 days")
                .build();
        Trip trip = new Trip().toBuilder()
                .price(1200L)
                .city("Krakow")
                .description("Nice tridvsdvdsvsdvsdvp")
                .url("https://cdn.pixabay.com/photo/2015/10/23/07/11/bieszczady-1002425_1280.jpg")
                .length("5 days")
                .build();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(tripDto);
        //When & Then
        when(mapper.toTrip(any(TripDto.class))).thenReturn(trip);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));

        Mockito.verify(service).createNewTrip(trip);

    }

    @Test
    public void shouldUpdateTrip() throws Exception{
        //Given
        TripDto tripDto = new TripDto().toBuilder()
                .id(1L)
                .price(1200L)
                .city("Krakow")
                .description("Nice tridvsdvdsvsdvsdvp")
                .length("5 days")
                .build();

        //When & Then
        when(service.updateTrip(any(TripDto.class))).thenReturn(tripDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(tripDto);
        mockMvc.perform(put("/v1/trips").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.price",is(1200)))
                .andExpect(jsonPath("$.city",is("Krakow")))
                .andExpect(jsonPath("$.description",is("Nice tridvsdvdsvsdvsdvp")))
                .andExpect(jsonPath("$.length",is("5 days")));
    }

    @Test
    public void shouldDeleteTrip() throws Exception{
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/trips/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));


        Mockito.verify(service).deleteTrip(1L);
    }
}