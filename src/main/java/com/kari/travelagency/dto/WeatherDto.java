package com.kari.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {
    private String minimalTemp;
    private String maximalTemp;
    private String weatherState;
    private String date;
    private String iconUrl;

    @Override
    public String toString() {
        return "WeatherDto{" +
                "minimalTemp=" + minimalTemp +
                ", maximalTemp=" + maximalTemp +
                ", weatherState='" + weatherState + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
