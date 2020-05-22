package com.kari.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String arrivedAirport;

    private String departedAirport;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int amountOfParticipants;

    private Long price;

    private String foodOption;

    private String description;

    private String standard;

    private int length;

    private LocalDate startingTime;

    private LocalDate finishTime;

    private String photoUrl;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "opinion_id")
    private Opinion opinion;

    private boolean isComplaint;

    public Trip(String arrivedAirport, String departedAirport, User user, int amountOfParticipants, Long price,
                String foodOption, String description, String standard, int length, LocalDate startingTime,
                LocalDate finishTime, String photoUrl, Opinion opinion) {
        this.arrivedAirport = arrivedAirport;
        this.departedAirport = departedAirport;
        this.user = user;
        this.amountOfParticipants = amountOfParticipants;
        this.price = price;
        this.foodOption = foodOption;
        this.description = description;
        this.standard = standard;
        this.length = length;
        this.startingTime = startingTime;
        this.finishTime = finishTime;
        this.photoUrl = photoUrl;
        this.opinion = opinion;
    }

    public Trip(Long id, String arrivedAirport, String departedAirport, User user) {
        this.id = id;
        this.arrivedAirport = arrivedAirport;
        this.departedAirport = departedAirport;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", amountOfParticipants=" + amountOfParticipants +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
