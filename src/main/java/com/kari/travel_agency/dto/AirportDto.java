package com.kari.travel_agency.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AirportDto {

    private Long id;

    private String name;

    private CountryDto country;

    private List<Long> tripsEndingInId = new ArrayList<>();

    private List<Long> tripsStartingInId = new ArrayList<>();
}
