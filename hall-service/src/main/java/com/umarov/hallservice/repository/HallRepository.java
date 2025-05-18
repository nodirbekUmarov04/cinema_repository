package com.umarov.hallservice.repository;

import com.umarov.hallservice.entity.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Integer> {
    Hall getHallById(Long id);

    boolean existsHallById(Long id);
}
