package com.umarov.showtimeservice.dto;

import com.umarov.showtimeservice.entity.Seat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowtimeResponseBody {
    private Long id;
    private String hallName;
    private String title;
    private String genre;
    private String description;
    private LocalDate premiereDate;
    private LocalDateTime startTime;
    private List<Seat> seats;
}
