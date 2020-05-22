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
    private ArticleRepository repository;

    public Article toArticle(ArticleDto articleDto){
        return new Article(articleDto.getId(), articleDto.getTitle(),articleDto.getText(),
                articleDto.getPhotoUrl(), articleDto.getCity());
    }

    public ArticleDto toArticleDto(Article article){
        return new ArticleDto(article.getId(), article.getTitle(),article.getText(),
                article.getPhotoUrl(), article.getCity());
    }

    public List<ArticleDto> toArticleDtoList(List<Article> list){
        return list.stream()
                .map(article -> toArticleDto(article))
                .collect(Collectors.toList());
    }
}
