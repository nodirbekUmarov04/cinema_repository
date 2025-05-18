package com.umarov.bookingservice.controller;

import com.umarov.bookingservice.dto.BookingAddRequest;
import com.umarov.bookingservice.dto.BookingResponse;
import com.umarov.bookingservice.service.BookingService;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/booking")
public class BookingController {
    private final BookingService bookingService;

    @GetMapping("/{id}")
    public ResponseEntity<List<BookingResponse>> getUserBookings(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.SC_OK).body(bookingService.getUserBookings(id));
    }

    @PostMapping
    public ResponseEntity<String> addBooking(@RequestBody BookingAddRequest request) {
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(bookingService.addBooking(request));
    }

    @PostMapping("/process-payment/{id}")
    public ResponseEntity<String> payForBooking(@PathVariable String id) {
        try {
            bookingService.payForBooking(id);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body("Successfully payed for booking");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/cancel-booking/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.status(HttpStatus.SC_OK).body("Successfully cancelled booking");
    }


}
