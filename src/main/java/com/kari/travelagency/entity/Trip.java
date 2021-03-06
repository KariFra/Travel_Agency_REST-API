package com.kari.travelagency.entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "traveller_id")
    private Traveller traveller;

    private Long price;

    private String city;

    private String url;

    @Size(min=10, max=400)
    private String description;

    private String length;

    private String food;

    private int stars;


    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "trip_additions", joinColumns = @JoinColumn(name = "trip_id"))
    private List<String> additions;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trip trip = (Trip) o;

        return id.equals(trip.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }


}
