package com.kari.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Traveller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String mail;

    private String password;

    private String role;

    private String avatarUrl;

    @OneToMany(targetEntity = Trip.class,
            mappedBy = "traveller",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Trip> trips;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "traveller_opinions", joinColumns = @JoinColumn(name = "traveller_id"))
    private List<String> opinions;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Traveller traveller = (Traveller) o;

        return id.equals(traveller.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "Traveller{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", trips=" + trips +
                ", opinions=" + opinions +
                '}';
    }
}
