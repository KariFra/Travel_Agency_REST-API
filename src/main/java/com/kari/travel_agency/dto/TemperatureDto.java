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

    private int degree;

    private String description;

    private String url;

    private LocalDate dateBeginning;

    private int amountOfDays;

    private String city;
}
