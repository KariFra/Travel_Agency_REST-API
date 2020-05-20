package com.kari.travel_agency.repository;

import com.kari.travel_agency.entity.CurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<CurrencyRate,Long> {
}
