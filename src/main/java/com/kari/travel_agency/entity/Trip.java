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
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airport_arrived")
    private Airport arrived;

    @ManyToOne
    @JoinColumn(name = "airport_departed")
    private Airport departed;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int amountOfParticipants;

    private Long price;

    private String foodOption;

    private String standard;

    private int length;

    private LocalDate startingTime;

    private LocalDate finishTime;

    private String photoUrl;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "opinion_id")
    private Opinion opinion;

    private boolean isComplaint;

}
