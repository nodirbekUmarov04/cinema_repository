package com.umarov.movieservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MovieAddRequest {
    private String title;
    private String genre;
    private String description;
    private Integer durationMinutes;
    private LocalDate releaseDate;
}
