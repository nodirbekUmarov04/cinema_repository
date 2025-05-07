package com.umarov.userservice.repository;

import com.umarov.userservice.model.RoleData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleData, Long> {
    Optional<RoleData> findByName(String name);
}