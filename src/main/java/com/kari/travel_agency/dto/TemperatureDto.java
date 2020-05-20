package com.kari.travel_agency.dto;

import com.kari.travel_agency.entity.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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

    private List<Long> citiesId = new ArrayList<>();
}
