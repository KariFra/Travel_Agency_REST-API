package com.kari.travelagency.service;

import com.kari.travelagency.dto.ArticleDto;
import com.kari.travelagency.entity.Article;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.ArticleMapper;
import com.kari.travelagency.repository.ArticleRepository;
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

    public Article createArticle(Article article){
        Article newArticle = new Article(article.getTitle(), article.getText(),
                article.getPhotoUrl(), article.getCity());
        return repository.save(newArticle);
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
