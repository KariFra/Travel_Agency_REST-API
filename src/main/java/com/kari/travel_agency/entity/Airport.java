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
public class Airport {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(targetEntity = Trip.class,
            mappedBy = "arrived",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Trip> tripsEndingIn;

    @OneToMany(targetEntity = Trip.class,
            mappedBy = "departed",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Trip> tripsStartingIn;






}
