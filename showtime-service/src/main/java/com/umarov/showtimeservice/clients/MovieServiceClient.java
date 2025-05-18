package com.umarov.showtimeservice.clients;

import com.umarov.showtimeservice.dto.MovieResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service")
public interface MovieServiceClient {

    @GetMapping("/api/movies/{id}")
    MovieResponse getMovieById(@PathVariable("id") Long id);
}
