package com.kari.travel_agency.repository;

import com.kari.travel_agency.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
