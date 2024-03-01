package com.example.demoservice.repository;

import com.example.demoservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Date-2/29/2024
 * By Sardor Tokhirov
 * Time-10:09 AM (GMT+5)
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String username);


}
