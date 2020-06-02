package com.kari.travelagency.controller;

import com.kari.travelagency.dto.ArticleDto;
import com.kari.travelagency.mapper.ArticleMapper;
import com.kari.travelagency.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
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

    @GetMapping("/{id}")
    public ArticleDto getArticle(@PathVariable Long id){
        return service.getArticle(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto createArticle(@RequestBody ArticleDto articleDto){
        return mapper.toArticleDto(service.createArticle(mapper.toArticle(articleDto)));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ArticleDto updateArticle(@RequestBody ArticleDto articleDto){
        return service.updateArticle(articleDto);
    }

    @DeleteMapping("/{id}")
    public void  deleteArticle(@PathVariable Long id) {service.deleteArticle(id);}
}
