package com.umarov.bookingservice.dto;

import lombok.Data;

@Data
public class BookingAddRequest {
    private Long userId;
    private Long showtimeId;
    private int rowNumber;
    private int seatNumber;
}
