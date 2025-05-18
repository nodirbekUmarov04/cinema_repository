package com.umarov.movieservice.service;

import com.umarov.movieservice.clients.UserServiceClient;
import com.umarov.movieservice.dto.*;
import com.umarov.movieservice.entity.Movie;
import com.umarov.movieservice.entity.Review;
import com.umarov.movieservice.repository.MovieRepository;
import com.umarov.movieservice.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ReviewRepository reviewRepository;
    private final UserServiceClient userServiceClient;

    public List<MovieResponse> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(
                MovieService::toMovieResponse
        ).toList();
    }

    public MovieResponse getMovieById(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found");
        }
        return toMovieResponse(movieRepository.getMovieById((id)));
    }

    public MovieResponse createMovie(MovieAddRequest request) {
        Movie savedMovie = movieRepository.save(toMovie(request));

        return toMovieResponse(savedMovie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public static MovieResponse toMovieResponse(Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .description(movie.getDescription())
                .durationMinutes(movie.getDurationMinutes())
                .releaseDate(movie.getReleaseDate())
                .build();
    }

    public static Movie toMovie(MovieAddRequest request) {
        return Movie.builder()
                .title(request.getTitle())
                .genre(request.getGenre())
                .description(request.getDescription())
                .durationMinutes(request.getDurationMinutes())
                .releaseDate(request.getReleaseDate())
                .build();
    }

    public void addReview(Long id, ReviewAddRequest request) {
        Movie movie = movieRepository.getMovieById((id));
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }

        UserResponse user = userServiceClient.getUser(request.getUserId()).getBody();
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Review review = new Review();
        review.setAuthor(user.getUsername());
        review.setMovie(movie);
        review.setContent(request.getReview());
        reviewRepository.save(review);
    }

    public List<ReviewResponseBody> getReviews(Long id) {
        Movie movie = movieRepository.getMovieById((id));
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }

        return reviewRepository.getReviewsByMovie(movie).stream().map(
                MovieService::toReviewResponseBody
        ).toList();
    }

    public static ReviewResponseBody toReviewResponseBody(Review review) {
        return ReviewResponseBody.builder()
                .movie(review.getMovie().getTitle())
                .author(review.getAuthor())
                .content(review.getContent())
                .build();
    }
}