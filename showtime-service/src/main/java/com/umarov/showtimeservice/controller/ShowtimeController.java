package com.umarov.showtimeservice.controller;

import com.umarov.showtimeservice.dto.ShowtimeAddRequest;
import com.umarov.showtimeservice.dto.ShowtimeResponseBody;
import com.umarov.showtimeservice.service.ShowtimeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
@AllArgsConstructor
public class ShowtimeController {
    private static final Logger log = LoggerFactory.getLogger(ShowtimeController.class);
    private ShowtimeService showtimeService;

    @PostMapping("/add")
    public ResponseEntity<ShowtimeResponseBody> post(@RequestBody ShowtimeAddRequest request) {
        return ResponseEntity.ok(showtimeService.createShowtime(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<ShowtimeResponseBody>> getAllShowtime() {
        return new ResponseEntity<>(showtimeService.getAllShowtime(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ShowtimeResponseBody> getShowtimeById(@PathVariable Long id) {
        return new ResponseEntity<>(showtimeService.getShowtimeById(id), HttpStatus.OK);
    }

    @PostMapping("/{id}/take-seat/{row}/{seat}")
    public void takeSeat(@PathVariable Long id, @PathVariable int row, @PathVariable int seat) {
        log.info("Taking seat {} of row {} and seat {}", row, row, seat);
        showtimeService.takeSeat(id,row,seat);
    }

    @PostMapping("/{id}/return-seat/{row}/{seat}")
    public void returnSeat(@PathVariable Long id, @PathVariable int row, @PathVariable int seat) {
        showtimeService.returnSeat(id,row,seat);
    }

    @GetMapping("/today")
    public ResponseEntity<List<ShowtimeResponseBody>> getAllShowtimeToday() {
        return new ResponseEntity<>(showtimeService.getAllShowtimeToday(), HttpStatus.OK);
    }



}
