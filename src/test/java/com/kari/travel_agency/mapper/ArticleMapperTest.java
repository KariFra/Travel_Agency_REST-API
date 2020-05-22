package com.kari.travel_agency.mapper;


import com.kari.travel_agency.dto.ArticleDto;
import com.kari.travel_agency.entity.Article;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArticleMapperTest {

    @Test
    public void mapToArticleDtoAndBack(){
        //Given
        Article article = new Article("Mermaid life", "Once upon a time...",
                "www.google.pl","Warsaw");
        ArticleMapper mapper = new ArticleMapper();
        //When
        ArticleDto articleDto = mapper.toArticleDto(article);
        Article newArticle = mapper.toArticle(articleDto);
        //Than
        assertEquals("Warsaw",articleDto.getCity());
        assertEquals("Once upon a time...",newArticle.getText());
    }

    @Test
    public void mapToArticleDtoList(){
        //Given
        Article articleOne = new Article("Mermaid life", "The sea is cold",
                "www.google.pl","Warsaw");
        Article articleTwo = new Article("Awesome journey to the sea", "Once upon a time...",
                "www.yahoo.pl","Gdansk");
        List<Article> list = new ArrayList<>();
        list.add(articleOne);
        list.add(articleTwo);
        ArticleMapper mapper = new ArticleMapper();
        //When
        List<ArticleDto> dtoList = mapper.toArticleDtoList(list);

        //Than
        assertEquals("Gdansk",dtoList.get(1).getCity());
    }

}