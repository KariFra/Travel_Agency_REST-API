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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String avatarUrl;

    @OneToMany(targetEntity = Trip.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Trip> trips;

    @OneToMany(targetEntity = Opinion.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Opinion> opinionsGiven;


    public User(String firstName, String lastName, String address, String avatarUrl) {
    }

    public User(Long id, String firstName, String lastName, String address, String avatarUrl) {
    }
}
