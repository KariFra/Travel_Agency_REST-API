package com.kari.travelagency.dto;


import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String mail;

    private String password;

    private String role;

    private String avatarUrl;

    private List<Long> tripsId;




    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
