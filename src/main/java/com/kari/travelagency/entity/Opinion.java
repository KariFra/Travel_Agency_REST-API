package com.kari.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min=10, max=1000)
    private String message;

    private String travellerUrl;

    private int rating;

    public Opinion(String message, String travellerUrl, int rating){
        this.message = message;
        this.travellerUrl = travellerUrl;
        this.rating = rating;
    }
}
