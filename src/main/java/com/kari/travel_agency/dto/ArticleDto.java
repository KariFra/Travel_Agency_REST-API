package com.kari.travel_agency.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private Long id;

    private String title;

    private String text;

    private String photoUrl;

    private String city;
}
