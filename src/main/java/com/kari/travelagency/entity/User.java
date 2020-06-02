package com.kari.travelagency.entity;

import com.kari.travelagency.security.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {

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
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Trip> trips;

    @OneToMany(targetEntity = Opinion.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Opinion> opinionsGiven;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
