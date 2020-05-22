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
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

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
