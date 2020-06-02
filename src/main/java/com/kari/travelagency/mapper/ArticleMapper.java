package com.kari.travelagency.mapper;

import com.kari.travelagency.dto.ArticleDto;
import com.kari.travelagency.entity.Article;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {



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
