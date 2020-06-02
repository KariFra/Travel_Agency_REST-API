package com.kari.travelagency.repository;

import com.kari.travelagency.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip,Long>{
}
