package com.umarov.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieResponse {
    private Long id;
    private String title;
    private String genre;
    private String description;
    private Integer durationMinutes;
    private LocalDate releaseDate;
}
