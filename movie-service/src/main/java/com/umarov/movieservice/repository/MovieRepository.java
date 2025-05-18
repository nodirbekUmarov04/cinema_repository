package com.umarov.movieservice.repository;

import com.umarov.movieservice.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie getMovieById(Long id);
}