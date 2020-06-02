package com.kari.travelagency.controller;

import com.google.gson.Gson;
import com.kari.travelagency.dto.ArticleDto;

import com.kari.travelagency.entity.Article;
import com.kari.travelagency.exception.NotFoundException;
import com.kari.travelagency.mapper.ArticleMapper;
import com.kari.travelagency.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;




@RunWith(SpringRunner.class)
@WebMvcTest(ArticleController.class)
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService service;

    @MockBean
    private ArticleMapper mapper;

    @Test
    public void shouldGetArticles() throws Exception{
        //Given
        List<ArticleDto> list = new ArrayList<>();
        list.add(new ArticleDto(1L,"Mermaid life", "Once upon a time...............",
                "www.google.pl","Warsaw"));
        //When & Then
        when(service.getArticles()).thenReturn(list);
        mockMvc.perform(get("/v1/articles").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].title",is("Mermaid life")))
                .andExpect(jsonPath("$[0].text",is("Once upon a time...............")))
                .andExpect(jsonPath("$[0].photoUrl",is("www.google.pl")))
                .andExpect(jsonPath("$[0].city",is("Warsaw")));

    }
    @Test
    public void shouldGetArticle() throws Exception{
        //Given
        ArticleDto articleDto = new ArticleDto(1L,"Mermaid life", "Once upon a time...............",
                "www.google.pl","Warsaw");
        //When & Then
        when(service.getArticle(1L)).thenReturn(articleDto);
        mockMvc.perform(get("/v1/articles/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("Mermaid life")))
                .andExpect(jsonPath("$.text",is("Once upon a time...............")))
                .andExpect(jsonPath("$.photoUrl",is("www.google.pl")))
                .andExpect(jsonPath("$.city",is("Warsaw")));
    }

    @Test
    public void noArticle() throws Exception{
        //When & Then
        when(service.getArticle(1L)).thenReturn(null);
        try {
            mockMvc.perform(get("/v1/articles/1")
                    .characterEncoding("UTF-8"))
                    .andExpect(status().is(200));
        } catch (NestedServletException e){
            assertTrue(e.getCause() instanceof NotFoundException);
        }
    }

    @Test
    public void shouldCreateArticle() throws Exception{
        //Given
        ArticleDto articleDto = new ArticleDto(1L,"Mermaid life", "Once upon a time...............",
                "www.google.pl","Warsaw");
        Article article = new Article(1L,"Mermaid life", "Once upon a time...............",
                "www.google.pl","Warsaw");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(articleDto);
        //When & Then
        when(mapper.toArticle(any(ArticleDto.class))).thenReturn(article);
        when(service.createArticle(article)).thenReturn(article);
        when(mapper.toArticleDto(any(Article.class))).thenReturn(articleDto);
        mockMvc.perform(post("/v1/articles").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("Mermaid life")))
                .andExpect(jsonPath("$.text",is("Once upon a time...............")))
                .andExpect(jsonPath("$.photoUrl",is("www.google.pl")))
                .andExpect(jsonPath("$.city",is("Warsaw")));

    }

    @Test
    public void shouldUpdateArticle() throws Exception{
        //Given
        ArticleDto articleDto = new ArticleDto(1L,"Mermaid life", "Once upon a time...............",
                "www.google.pl","Warsaw");

        //When & Then
        when(service.updateArticle(any(ArticleDto.class))).thenReturn(articleDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(articleDto);
        mockMvc.perform(put("/v1/articles").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("Mermaid life")))
                .andExpect(jsonPath("$.text",is("Once upon a time...............")))
                .andExpect(jsonPath("$.photoUrl",is("www.google.pl")))
                .andExpect(jsonPath("$.city",is("Warsaw")));
    }

    @Test
    public void shouldDeleteArticle() throws Exception{
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/articles/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));


        Mockito.verify(service).deleteArticle(1L);
    }

}