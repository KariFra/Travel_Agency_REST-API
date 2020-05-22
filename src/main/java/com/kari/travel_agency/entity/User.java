package com.kari.travel_agency.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public User(Long id, String firstName, String lastName, String address, String avatarUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.avatarUrl = avatarUrl;
    }

    public User(String firstName, String lastName, String address, String avatarUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
