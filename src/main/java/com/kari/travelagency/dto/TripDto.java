package com.kari.travelagency.dto;



import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {

    private Long id;

    private Long userId;

    private Long price;

    private String city;

    private String url;

    private String description;

    private String length;

    private String food;

    private int stars;

    private List<String> additions;



    @Override
    public String toString() {
        return "TripDto{" +
                "id=" + id +
//                ", userId=" + userId +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", length=" + length +
                ", additions=" + additions +
                '}';
    }


}
