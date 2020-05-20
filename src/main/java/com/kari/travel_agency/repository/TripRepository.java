package com.kari.travel_agency.repository;

import com.kari.travel_agency.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long>{
}
