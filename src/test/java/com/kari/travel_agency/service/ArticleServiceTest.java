package com.kari.travel_agency.service;

import com.kari.travel_agency.dto.ArticleDto;
import com.kari.travel_agency.entity.Article;
import com.kari.travel_agency.exception.NotFoundException;
import com.kari.travel_agency.mapper.ArticleMapper;
import com.kari.travel_agency.repository.ArticleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceTest.class);

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private ArticleService service;

    @Autowired
    private ArticleMapper mapper;

    private Long articleOneId = 0L;

    @Before
    public void prep(){
        LOGGER.info("Putting two articles to database.");
        Article articleOne = new Article("First article", "Lalalal","www.google.pl","Warsaw");
        Article articleTwo = new Article("Second article", "lololo","www.google.pl","Krakow");
        List<Article> list = new ArrayList<>();
        list.add(articleOne);
        list.add(articleTwo);
        repository.saveAll(list);
        articleOneId = articleOne.getId();
    }

    @Test
    public void shouldGetArticles(){
        LOGGER.info("Getting articles.");
        //When
        List<ArticleDto> articleList = service.getArticles();
        //Than
        assertTrue(articleList.size()!=0);
        assertEquals("Warsaw",articleList.get(0).getCity());
    }

    @Test
    public void shouldGetArticle(){
        LOGGER.info("Getting one article");
        //When
        List<Article> list = repository.findAll();
        ArticleDto articleDto = service.getArticle(articleOneId);

        //Than
        assertEquals("Warsaw",articleDto.getCity());
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetUserException() throws NotFoundException{
        //When
        ArticleDto articleDto = service.getArticle(50L);
    }

    @Test
    public void shouldUpdateArticle(){
        LOGGER.info("Updating article.");
        //Given
        Article updatedArticle = repository.getOne(articleOneId);
        //When
        updatedArticle.setTitle("Changed title");
        updatedArticle.setText("Changed text");
        service.updateArticle(mapper.toArticleDto(updatedArticle));
        //Than
        assertEquals("Changed title",repository.getOne(articleOneId).getTitle());
        assertEquals("Changed text",repository.getOne(articleOneId).getText());
    }

    @Test
    public void shouldCreateArticle(){
        LOGGER.info("Creating article");
        //Given
        Article article = new Article("Some article", "Article's text","www.google.pl","Warsaw");
        List<Article> listOne = repository.findAll();
        int sizeOne = listOne.size();
        service.createArticle(article);
        //When
        List<Article> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();

        //Than
        assertTrue(sizeTwo - sizeOne == 1);
    }

    @Test
    public void shouldDeleteArticle(){
        LOGGER.info("Deleting all of the articles.");
        //When
        List<Article> list = repository.findAll();
        for(int i=0; i<list.size();i++) {
            Long num = list.get(i).getId();
            service.deleteArticle(num);
        }
        //Than
        assertTrue(repository.findAll().size()==0);
    }
}