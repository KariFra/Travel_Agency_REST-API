package com.kari.travelagency.controller;

import com.google.gson.Gson;
import com.kari.travelagency.dto.UserDto;
import com.kari.travelagency.entity.User;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.UserMapper;
import com.kari.travelagency.service.UserService;
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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    @MockBean
    private UserMapper mapper;

    @Test
    public void shouldGetUsers() throws Exception{
        //Given
        List<UserDto> list = new ArrayList<>();
        list.add(new UserDto().toBuilder()
                .id(1L)
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:ball.svg")
                .build());
        //When & Then
        when(service.getUsers()).thenReturn(list);
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
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
        UserDto userDto = new UserDto().toBuilder()
                .id(1L)
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:ball.svg")
                .build();
        //When & Then
        when(service.getUser(1L)).thenReturn(userDto);
        mockMvc.perform(get("/v1/users/1").contentType(MediaType.APPLICATION_JSON))
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
            mockMvc.perform(get("/v1/users/1")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().is(200));
        } catch (NestedServletException e){
            assertTrue(e.getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void shouldCreateUser() throws Exception{
        //Given
        User user = new User().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        UserDto userDto = new UserDto().toBuilder()
                .firstName("Joanna")
                .lastName("Mroz")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:tree.svg")
                .build();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When & Then
        when(mapper.toUser(any(UserDto.class))).thenReturn(user);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200));

        Mockito.verify(service).createNewUser(user);

    }

    @Test
    public void shouldUpdateUser() throws Exception{
        //Given
        UserDto userDto = new UserDto().toBuilder()
                .id(1L)
                .firstName("Marc")
                .lastName("Bell")
                .mail("user@mail.com")
                .avatarUrl("https://avatars.dicebear.com/api/bottts/:ball.svg")
                .build();

        //When & Then
        when(service.updateUser(any(UserDto.class))).thenReturn(userDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        mockMvc.perform(put("/v1/users").contentType(MediaType.APPLICATION_JSON)
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
                .delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));


        Mockito.verify(service).deleteUser(1L);
    }

}