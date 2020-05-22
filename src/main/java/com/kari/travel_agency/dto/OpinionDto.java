package com.kari.travel_agency.dto;


import com.kari.travel_agency.entity.User;
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

    public OpinionDto(String message, int rating) {
        this.message = message;
        this.rating = rating;
    }

    public OpinionDto(Long id, String message, int rating) {
        this.id = id;
        this.message = message;
        this.rating = rating;
    }
}
