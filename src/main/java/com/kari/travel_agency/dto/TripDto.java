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

    private String arrivedAirport;

    private String departedAirport;

    private Long userId;

    private int amountOfParticipants;

    private Long price;

    private String foodOption;

    private String description;

    private String standard;

    private int length;

    private LocalDate startingTime;

    private LocalDate finishTime;

    private String photoUrl;

    private Long opinionId;

    private boolean isComplaint;


    public TripDto(Long id, String arrivedAirport, String departedAirport, int amountOfParticipants, Long price,
                String foodOption, String description, String standard, int length, LocalDate startingTime,
                LocalDate finishTime, String photoUrl) {
        this.id = id;
        this.arrivedAirport = arrivedAirport;
        this.departedAirport = departedAirport;
        this.amountOfParticipants = amountOfParticipants;
        this.price = price;
        this.foodOption = foodOption;
        this.description = description;
        this.standard = standard;
        this.length = length;
        this.startingTime = startingTime;
        this.finishTime = finishTime;
        this.photoUrl = photoUrl;
    }

    public TripDto(String arrivedAirport, String departedAirport, int amountOfParticipants, Long price,
                String foodOption, String description, String standard, int length, LocalDate startingTime,
                LocalDate finishTime, String photoUrl) {
        this.arrivedAirport = arrivedAirport;
        this.departedAirport = departedAirport;
        this.amountOfParticipants = amountOfParticipants;
        this.price = price;
        this.foodOption = foodOption;
        this.description = description;
        this.standard = standard;
        this.length = length;
        this.startingTime = startingTime;
        this.finishTime = finishTime;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "TripDto{" +
                "id=" + id +
                ", arrivedAirport='" + arrivedAirport + '\'' +
                ", departedAirport='" + departedAirport + '\'' +
                ", userId=" + userId +
                '}';
    }
}
