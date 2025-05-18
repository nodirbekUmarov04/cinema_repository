package com.umarov.showtimeservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieResponse {
    private Long id;
    private String title;
    private String genre;
    private String description;
    private Integer durationMinutes;
    private LocalDate releaseDate;
}
