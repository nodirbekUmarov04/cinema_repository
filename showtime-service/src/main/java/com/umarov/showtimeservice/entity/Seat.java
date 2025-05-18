package com.umarov.showtimeservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rowNumber;
    private int seatNumber;
    private boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "showtime_id")
    @JsonIgnore
    private Showtime showtime;
}

