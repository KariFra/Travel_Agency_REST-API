package com.kari.travelagency.repository;

import com.kari.travelagency.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByMail(String mail);
}
