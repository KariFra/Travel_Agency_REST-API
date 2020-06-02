package com.kari.travelagency.client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kari.travelagency.dto.WeatherDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;


@Component
public class WeatherClient {
    private static DecimalFormat decForm = new DecimalFormat("0.00");
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherClient.class);

    @Value("${weather.api.endpoint.prod}")
    private String apiEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    private String cityNumber;
    private WeatherDto[] fiveDaysList = new WeatherDto[6];
    private double minimalTemp;
    private double maximalTemp;
    private String weatherState;
    private String date;
    private String iconUrl;


    public WeatherDto[] getWeatherDays(String city){
        try {
            JsonArray array = getRightJson(city);
            for (int i = 0; i < array.size(); i++) {
                weatherState = array.get(i).getAsJsonObject().get("weather_state_name").getAsString();
                iconUrl = getRightIcon(weatherState);
                minimalTemp = array.get(i).getAsJsonObject().get("min_temp").getAsDouble();
                maximalTemp = array.get(i).getAsJsonObject().get("max_temp").getAsDouble();
                date = array.get(i).getAsJsonObject().get("applicable_date").getAsString();
                fiveDaysList[i] = new WeatherDto(decForm.format(minimalTemp), decForm.format(maximalTemp),
                        weatherState, date, iconUrl);
            }
            return fiveDaysList;
        }catch (RestClientException e) {
            LOGGER.error(e.getMessage(),e);
            return new WeatherDto[0];
        }
    }

    public WeatherDto getWeatherDay(String city){
        JsonArray array = getRightJson(city);
        weatherState = array.get(0).getAsJsonObject().get("weather_state_name").getAsString();
        iconUrl = getRightIcon(weatherState);
        minimalTemp = array.get(0).getAsJsonObject().get("min_temp").getAsDouble();
        maximalTemp = array.get(0).getAsJsonObject().get("max_temp").getAsDouble();
        date = array.get(0).getAsJsonObject().get("applicable_date").getAsString();
        return new WeatherDto(decForm.format(minimalTemp), decForm.format(maximalTemp), weatherState,
                date, iconUrl);
    }

    public JsonArray getRightJson(String city){
        String result = restTemplate.getForObject(apiEndpoint + "search/?query=" + city,String.class);
        JsonArray array = JsonParser.parseString(result).getAsJsonArray();
        cityNumber = array.get(0).getAsJsonObject().get("woeid").getAsString();
        String finalResult = restTemplate.getForObject(apiEndpoint + cityNumber,String.class);
        JsonObject jsonObjectFinal = JsonParser.parseString(finalResult).getAsJsonObject();
        JsonArray arrayFinal = jsonObjectFinal.getAsJsonArray("consolidated_weather");
        return arrayFinal;
    }

    public String getRightIcon(String weather){
        switch(weather){
            case "Clear":
                iconUrl = "https://www.metaweather.com/static/img/weather/c.svg";
                break;
            case "Snow":
                iconUrl = "https://www.metaweather.com/static/img/weather/sn.svg";
                break;
            case "Sleet":
                iconUrl = "https://www.metaweather.com/static/img/weather/sl.svg";
                break;
            case "Hail":
                iconUrl = "https://www.metaweather.com/static/img/weather/h.svg";
                break;
            case "Thunderstorm":
                iconUrl = "https://www.metaweather.com/static/img/weather/t.svg";
                break;
            case "Heavy Rain":
                iconUrl = "https://www.metaweather.com/static/img/weather/hr.svg";
                break;
            case "Light Rain":
                iconUrl = "https://www.metaweather.com/static/img/weather/lr.svg";
                break;
            case "Showers":
                iconUrl = "https://www.metaweather.com/static/img/weather/s.svg";
                break;
            case "Heavy Cloud":
                iconUrl = "https://www.metaweather.com/static/img/weather/hc.svg";
                break;
            case "Light Cloud":
                iconUrl = "https://www.metaweather.com/static/img/weather/lc.svg";
                break;
        }
      return  iconUrl;
    }
}
