package com.umarov.showtimeservice.repository;

import com.umarov.showtimeservice.entity.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowtimeRepository extends JpaRepository<Showtime,Long> {

    Showtime findShowtimeById(Long id);

    List<Showtime> findShowtimesByPremiereDate(LocalDate premiereDate);
}
