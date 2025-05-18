package com.umarov.userservice.repository;

import com.umarov.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User getUserById(Long id);

    boolean existsByEmail(String email);
}