package com.kari.travelagency.controller;


import com.kari.travelagency.client.WeatherClient;
import com.kari.travelagency.dto.WeatherDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherClient client;

    @Test
    public void shouldGetEmptyArray() throws Exception{
        //When
        WeatherDto[] array = new WeatherDto[0];
        when(client.getWeatherDays("london")).thenReturn(array);

        //Than
        mockMvc.perform(get("/v1/weather/london").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void shouldGetArray() throws Exception{
        //When & Than
        mockMvc.perform(get("/v1/weather/london").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        Mockito.verify(client).getWeatherDays("london");
    }

    @Test
    public void shouldGetOneDay() throws Exception{
        //When & Than
        mockMvc.perform(get("/v1/weather/now/london").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        Mockito.verify(client).getWeatherDay("london");
    }


}