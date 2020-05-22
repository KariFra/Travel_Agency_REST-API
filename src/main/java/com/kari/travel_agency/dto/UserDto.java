package com.kari.travel_agency.dto;


import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String avatarUrl;

    private List<Long> tripsId = new ArrayList<>();

    private List<Long> opinionsId = new ArrayList<>();

    public UserDto(Long id, String firstName, String lastName, String address, String avatarUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.avatarUrl = avatarUrl;
    }

    public UserDto(String firstName, String lastName, String address, String avatarUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.avatarUrl = avatarUrl;
    }
}
