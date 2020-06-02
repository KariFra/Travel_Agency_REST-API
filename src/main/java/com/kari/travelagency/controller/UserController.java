package com.kari.travelagency.controller;

import com.kari.travelagency.dto.UserDto;
import com.kari.travelagency.mapper.UserMapper;
import com.kari.travelagency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;


    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody UserDto user){
        userService.createNewUser(mapper.toUser(user));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto update(@RequestBody UserDto user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.deleteUser(id);
    }
}