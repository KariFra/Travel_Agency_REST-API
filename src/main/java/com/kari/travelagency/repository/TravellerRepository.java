package com.kari.travelagency.repository;

import com.kari.travelagency.entity.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravellerRepository extends JpaRepository<Traveller,Long> {
}
