package com.umarov.movieservice.controller;

import com.umarov.movieservice.dto.MovieAddRequest;
import com.umarov.movieservice.dto.MovieResponse;
import com.umarov.movieservice.dto.ReviewAddRequest;
import com.umarov.movieservice.dto.ReviewResponseBody;
import com.umarov.movieservice.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public MovieResponse createMovie(@RequestBody MovieAddRequest movie) {
        return movieService.createMovie(movie);
    }

    @GetMapping
    public List<MovieResponse> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @PostMapping("/add-movie-review/{id}")
    public ResponseEntity<String> addMovieReview(@PathVariable Long id,
                                                 @RequestBody ReviewAddRequest reviewAddRequest) {
        movieService.addReview(id,reviewAddRequest);
        return new ResponseEntity<>("Review Addeds", HttpStatus.OK);
    }

    @GetMapping("/get-movie-reviews/{id}")
    public ResponseEntity<List<ReviewResponseBody>> getMovieReviews(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.getReviews(id) ,HttpStatus.OK);
    }
}