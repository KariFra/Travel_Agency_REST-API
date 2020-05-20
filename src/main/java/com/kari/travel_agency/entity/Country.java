package com.kari.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(targetEntity = City.class,
            mappedBy = "country",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<City> cities;

    @OneToMany(targetEntity = Airport.class,
            mappedBy = "country",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Airport> airports;

    private String currency;
}
