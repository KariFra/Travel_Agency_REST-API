package com.kari.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double degree;

    private String description;

    private String url;

    private LocalDate date;

    private String city;

    public Temperature(Long id, double degree, String description, LocalDate date, String city) {
        this.id =id;
        this.degree = degree;
        this.description = description;
        this.date = date;
        this.city = city;
    }

    public Temperature(double degree, String description, LocalDate date, String city) {
        this.degree = degree;
        this.description = description;
        this.date = date;
        this.city = city;
    }
}
