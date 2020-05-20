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
public class TripDto {

    private Long id;

    private AirportDto arrived;

    private AirportDto departed;

    private UserDto user;

    private int amountOfParticipants;

    private Long price;

    private String foodOption;

    private String standard;

    private int length;

    private LocalDate startingTime;

    private LocalDate finishTime;

    private String photoUrl;

    private OpinionDto opinion;

    private boolean isComplaint;

}
