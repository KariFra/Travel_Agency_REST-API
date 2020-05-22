package com.kari.travel_agency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int rating;

    public Opinion(String message, int rating) {
        this.message = message;
        this.rating = rating;
    }

    public Opinion(Long id, String message, int rating) {
        this.id = id;
        this.message = message;
        this.rating = rating;
    }
}
