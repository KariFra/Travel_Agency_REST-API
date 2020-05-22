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
    @GeneratedValue
    private Long id;

    private int degree;

    private String description;

    private String url;

    private LocalDate dateBeginning;

    private int amountOfDays;

    private String city;

}
