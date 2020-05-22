package com.kari.travel_agency.service;

import com.kari.travel_agency.dto.ArticleDto;
import com.kari.travel_agency.entity.Article;
import com.kari.travel_agency.exception.NotFoundException;
import com.kari.travel_agency.mapper.ArticleMapper;
import com.kari.travel_agency.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper mapper;

    @Autowired
    private ArticleRepository repository;

    public List<ArticleDto> getArticles(){
        return mapper.toArticleDtoList(repository.findAll());
    }

    public ArticleDto getArticle(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Article with the id number: "+id+" was not found"));
        return mapper.toArticleDto(repository.getOne(id));
    }

    public void createArticle(Article article){
        Article newArticle = new Article("Interesting stuff","I was doing stuff","www.google.pl",
                "Warsaw");
        repository.save(newArticle);
    }

    public ArticleDto updateArticle(ArticleDto articleDto){
        Article updatedArticle = repository.getOne(articleDto.getId());
        updatedArticle.setTitle(articleDto.getTitle());
        updatedArticle.setText(articleDto.getText());
        updatedArticle.setCity(articleDto.getCity());
        updatedArticle.setPhotoUrl(articleDto.getPhotoUrl());
        return mapper.toArticleDto(repository.save(updatedArticle));
    }

    public void deleteArticle(Long id){
        repository.findById(id).orElseThrow(()->new NotFoundException("Article with the id number: "+id+" was not found"));
        repository.deleteById(id);
    }
}
