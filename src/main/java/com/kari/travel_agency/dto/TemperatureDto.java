package com.kari.travel_agency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureDto {

    private Long id;

    private double degree;

    private String description;

    private String url;

    private LocalDate date;

    private String city;

    public TemperatureDto(Long id, double degree, String description, LocalDate date, String city) {
        this.id =id;
        this.degree = degree;
        this.description = description;
        this.date = date;
        this.city = city;
    }

    public TemperatureDto(double degree, String description, LocalDate date, String city) {
        this.degree = degree;
        this.description = description;
        this.date = date;
        this.city = city;
    }
}
