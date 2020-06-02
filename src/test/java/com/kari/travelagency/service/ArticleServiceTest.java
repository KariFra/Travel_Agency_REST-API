package com.kari.travelagency.service;

import com.kari.travelagency.dto.ArticleDto;
import com.kari.travelagency.entity.Article;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.ArticleMapper;
import com.kari.travelagency.repository.ArticleRepository;
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
    private Long articleTwoId = 0L;
    private Long articleThreeId = 0L;

    @Before
    public void prep(){
        LOGGER.info("Putting two articles to database.");
        Article articleOne = new Article("First article", "Lalakhkhkhkhkhkkugyffffhfhflal","www.google.pl","Warsaw");
        Article articleTwo = new Article("Second article", "lolkjggyugyftftkfffufuyfyfyukfolo","www.google.pl","Krakow");
        List<Article> list = new ArrayList<>();
        list.add(articleOne);
        list.add(articleTwo);
        repository.saveAll(list);
        articleOneId = articleOne.getId();
        articleTwoId = articleTwo.getId();

    }

    @Test
    public void shouldGetArticles(){
        LOGGER.info("Getting articles.");
        //When
        List<ArticleDto> articleList = service.getArticles();
        //Than
        assertTrue(articleList.size()>=1);
        //Cleanup
        repository.deleteById(articleOneId);
        repository.deleteById(articleTwoId);

    }

    @Test
    public void shouldGetArticle(){
        LOGGER.info("Getting one article");
        //When
        List<Article> list = repository.findAll();
        ArticleDto articleDto = service.getArticle(articleOneId);

        //Than
        assertEquals("Warsaw",articleDto.getCity());

        //Cleanup
        repository.deleteById(articleOneId);
        repository.deleteById(articleTwoId);
    }

    @Test(expected = NotFoundException.class)
    public void shouldGetUserException() throws NotFoundException{
        //When
        service.getArticle(100000L);

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
        //Cleanup
        repository.deleteById(articleOneId);
        repository.deleteById(articleTwoId);
    }

    @Test
    public void shouldCreateArticle(){
        LOGGER.info("Creating article");
        //Given
        Article article = new Article("Some article", "tfktftfkytfytfkytfkytfArticle's text","www.google.pl","Warsaw");
        List<Article> listOne = repository.findAll();
        int sizeOne = listOne.size();
        articleThreeId = service.createArticle(article).getId();
        articleThreeId.toString();
        //When
        List<Article> listTwo = repository.findAll();
        int sizeTwo = listTwo.size();
        //Than
        assertTrue(sizeTwo - sizeOne == 1);
        //Cleanup
        repository.deleteById(articleOneId);
        repository.deleteById(articleTwoId);
        repository.deleteById(articleThreeId);
    }

    @Test
    public void shouldDeleteArticle(){
        LOGGER.info("Deleting all of the articles.");
        //When
        List<Article> listOne = repository.findAll();
        int sizeOne = listOne.size();
        service.deleteArticle(articleOneId);
        service.deleteArticle(articleTwoId);
        List<Article> listTwo = repository.findAll();
        int sizeTwo = listOne.size();
        //Than
        assertTrue(listOne.size() - listTwo.size() == 2);

    }
}