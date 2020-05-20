package com.kari.travel_agency.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {

    private Long id;

    private String name;

    private List<Long> citiesId;

    private List<Long> airports;

    private String currency;
}
