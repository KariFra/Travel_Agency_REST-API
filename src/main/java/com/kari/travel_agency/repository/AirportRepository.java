package com.kari.travel_agency.repository;

import com.kari.travel_agency.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {
}
