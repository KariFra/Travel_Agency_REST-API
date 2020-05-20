package com.kari.travel_agency.mapper;

import com.kari.travel_agency.dto.ArticleDto;
import com.kari.travel_agency.entity.Article;
import com.kari.travel_agency.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private ArticleRepository repository;

    public Article toArticle(ArticleDto articleDto){
        return new Article(articleDto.getId(), articleDto.getTitle(),articleDto.getText(),
                articleDto.getPhotoUrl(), cityMapper.toCity(articleDto.getCity()));
    }

    public ArticleDto toArticleDto(Article article){
        return new ArticleDto(article.getId(), article.getTitle(),article.getText(),
                article.getPhotoUrl(), cityMapper.toCityDto(article.getCity()));
    }

    public List<Article> toArticleList(List<Long> list){
        return list.stream()
                .map(element -> repository.getOne(element))
                .collect(Collectors.toList());
    }

    public List<Long> toArticleDtoList(List<Article> list){
        return list.stream()
                .map(article -> article.getId())
                .collect(Collectors.toList());
    }
}
