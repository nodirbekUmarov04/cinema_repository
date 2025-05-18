package com.umarov.showtimeservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ShowtimeAddRequest {
    private Long hallId;
    private Long movieId;
    private LocalDate premiereDate;
    private LocalDateTime startTime;
    private Double price;
}
