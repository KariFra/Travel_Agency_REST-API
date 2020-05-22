package com.kari.travel_agency.controller;

import com.kari.travel_agency.dto.ArticleDto;
import com.kari.travel_agency.mapper.ArticleMapper;
import com.kari.travel_agency.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/articles")
public class ArticleController {

    @Autowired
    private ArticleService service;

    @Autowired
    private ArticleMapper mapper;

    @GetMapping
    public List<ArticleDto> getArticles(){
        return service.getArticles();
    }

    @GetMapping(path = "/{id}")
    public ArticleDto getTrip(@PathVariable Long id){
        return service.getArticle(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createArticle(@RequestBody ArticleDto articleDto){
        service.createArticle(mapper.toArticle(articleDto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto updateTrip(@RequestBody ArticleDto articleDto){
        return service.updateArticle(articleDto);
    }
}
