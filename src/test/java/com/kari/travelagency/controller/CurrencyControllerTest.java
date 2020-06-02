package com.kari.travelagency.controller;

import com.kari.travelagency.client.CocktailClient;
import com.kari.travelagency.client.CurrencyClient;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyClient client;

    @Test
    public void shouldGetCocktail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/currency/usd")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        Mockito.verify(client).getRate("usd");
    }

}