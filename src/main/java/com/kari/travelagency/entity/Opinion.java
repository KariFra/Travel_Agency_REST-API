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

    @Size(min=10, max=400)
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private int rating;


    public Opinion(String message, UserEntity userEntity, int rating){
        this.message = message;
        this.userEntity = userEntity;
        this.rating = rating;
    }
}
