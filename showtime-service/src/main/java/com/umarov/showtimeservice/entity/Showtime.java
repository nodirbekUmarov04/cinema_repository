package com.umarov.showtimeservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Showtime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  hallName;
    private String title;
    private String genre;
    private String description;
    private Integer duration;
    private LocalDate premiereDate;
    private LocalDateTime startTime;
    private Double price;

    @OneToMany(mappedBy = "showtime", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;
}
