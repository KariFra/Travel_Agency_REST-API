package com.kari.travelagency.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpinionDto {

    private Long id;

    private String message;

    private Long userId;

    private int rating;

}
