package com.umarov.bookingservice.clients;

import com.umarov.bookingservice.dto.ShowtimeResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "showtime-service")
public interface ShowtimeServiceClient {
    @GetMapping("/api/showtimes/{id}")
    ShowtimeResponseBody getShowtimeById(@PathVariable Long id);

    @PostMapping("/api/showtimes/{id}/take-seat/{row}/{seat}")
    void takeSeat(@PathVariable Long id, @PathVariable int row, @PathVariable int seat);

    @PostMapping("/api/showtimes/{id}/return-seat/{row}/{seat}")
    void returnSeat(@PathVariable Long id, @PathVariable int row, @PathVariable int seat);
}
