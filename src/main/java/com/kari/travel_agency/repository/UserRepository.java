package com.kari.travel_agency.repository;

import com.kari.travel_agency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
