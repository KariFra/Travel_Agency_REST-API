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
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min=5, max=50)
    private String title;

    @Size(min=20, max=1000)
    private String text;

    private String photoUrl;

    private String city;

    public Article(String title, String text, String photoUrl, String city) {
        this.title = title;
        this.text = text;
        this.photoUrl = photoUrl;
        this.city = city;
    }
}
