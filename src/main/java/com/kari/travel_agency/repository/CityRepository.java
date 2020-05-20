package com.kari.travel_agency.repository;

import com.kari.travel_agency.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
