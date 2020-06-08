package com.kari.travelagency.controller;

import com.google.gson.Gson;
import com.kari.travelagency.dto.DrinkDto;
import com.kari.travelagency.entity.Drink;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.DrinkMapper;
import com.kari.travelagency.service.DrinkService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(DrinkController.class)
public class DrinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DrinkService service;

    @MockBean
    private DrinkMapper mapper;

    @Test
    public void shouldGetDrinks() throws Exception{
        //Given
        List<DrinkDto> list = new ArrayList<>();
        list.add(new DrinkDto(1L,"Bloody Merry","Take some stuff and mix"));

        //When & Than
        when(service.getDrinks()).thenReturn(list);
        mockMvc.perform(get("/v1/drink").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].name",is("Bloody Merry")))
                .andExpect(jsonPath("$[0].recipe",is("Take some stuff and mix")));


    }

    @Test
    public void shouldGetDrink() throws Exception {
        //Given

        DrinkDto drinkDto = new DrinkDto(1L, "Bloody Merry", "Take some stuff and mix");

        //When & Than
        when(service.getDrink(1L)).thenReturn(drinkDto);
        mockMvc.perform(get("/v1/drink/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Bloody Merry")))
                .andExpect(jsonPath("$.recipe", is("Take some stuff and mix")));
    }

    @Test
    public void shouldCreateDrink() throws Exception{
        //Given


        DrinkDto drinkDto = new DrinkDto(1L,"Bloody Merry","Take some stuff and mix");
        Drink drink = new Drink(1L,"Bloody Merry","Take some stuff and mix");

        //When & Than
        when(mapper.toDrink(any(DrinkDto.class))).thenReturn(drink);
        when(service.save(drink)).thenReturn(drink);
        when(mapper.toDrinkDto(any(Drink.class))).thenReturn(drinkDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(drinkDto);


        mockMvc.perform(post("/v1/drink").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.name", is("Bloody Merry")))
                .andExpect(jsonPath("$.recipe", is("Take some stuff and mix")));

    }

    @Test
    public void noDrink() throws Exception {
        //Given
        when(service.getDrink(1L)).thenReturn(null);

        //When & Than
        try {
            mockMvc.perform(get("/v1/drink/1")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().is(200));
        } catch (NestedServletException e){
            assertTrue(e.getCause() instanceof NotFoundException);
        }

    }


    @Test
    public void shouldDeleteDrink() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/drink/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));


        Mockito.verify(service).deleteDrink(1L);
    }

}