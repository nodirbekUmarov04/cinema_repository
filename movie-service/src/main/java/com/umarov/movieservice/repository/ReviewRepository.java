package com.umarov.movieservice.repository;

import com.umarov.movieservice.entity.Movie;
import com.umarov.movieservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> getReviewsByMovie(Movie movie);
}
