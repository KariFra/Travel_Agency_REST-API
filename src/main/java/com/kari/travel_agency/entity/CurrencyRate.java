package com.kari.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrencyRate {

    @Id
    @GeneratedValue
    private Long id;

    private String firstCurrency;

    private int firstCurrencyAmount;

    private String secondCurrency;

    private int secondCurrencyAmount;

    private LocalDate date;

}
