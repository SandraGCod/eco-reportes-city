package com.java.eco_reportes_city.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.eco_reportes_city.entity.User;

public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
