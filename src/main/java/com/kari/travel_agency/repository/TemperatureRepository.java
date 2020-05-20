package com.kari.travel_agency.repository;

import com.kari.travel_agency.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Temperature,Long> {
}
