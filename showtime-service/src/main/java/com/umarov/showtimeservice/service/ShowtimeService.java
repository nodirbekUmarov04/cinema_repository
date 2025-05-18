package com.umarov.showtimeservice.service;

import com.umarov.showtimeservice.clients.HallServiceClient;
import com.umarov.showtimeservice.clients.MovieServiceClient;
import com.umarov.showtimeservice.dto.HallResponse;
import com.umarov.showtimeservice.dto.MovieResponse;
import com.umarov.showtimeservice.dto.ShowtimeAddRequest;
import com.umarov.showtimeservice.dto.ShowtimeResponseBody;
import com.umarov.showtimeservice.entity.Seat;
import com.umarov.showtimeservice.entity.Showtime;
import com.umarov.showtimeservice.repository.ShowtimeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ShowtimeService {
    private final ShowtimeRepository showtimeRepository;
    private HallServiceClient hallServiceClient;
    private MovieServiceClient movieServiceClient;

    public List<ShowtimeResponseBody> getAllShowtime() {
        return showtimeRepository.findAll().stream().map(
                ShowtimeService::toShowtimeResponse
        ).toList();
    }

    public ShowtimeResponseBody createShowtime(ShowtimeAddRequest request) {
        HallResponse hall = hallServiceClient.getHallById(request.getHallId());
        if (hall == null) {
            throw new RuntimeException("Hall not found");
        }
        MovieResponse movie = movieServiceClient.getMovieById(request.getMovieId());
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }

        Showtime showtime = new Showtime();
        showtime.setHallName(hall.getName());
        showtime.setTitle(movie.getTitle());
        showtime.setGenre(movie.getGenre());
        showtime.setDescription(movie.getDescription());
        showtime.setPremiereDate(request.getPremiereDate());
        showtime.setStartTime(request.getStartTime());
        showtime.setPrice(request.getPrice());

        List<Seat> seats = new ArrayList<>();

        for (int row = 1; row <= hall.getTotalRows(); row++) {
            for (int number = 1; number <= hall.getSeatsPerRow(); number++) {
                Seat seat = new Seat();
                seat.setRowNumber(row);
                seat.setSeatNumber(number);
                seat.setShowtime(showtime);
                seats.add(seat);
            }
        }

        showtime.setSeats(seats);
        return toShowtimeResponse(showtimeRepository.save(showtime));
    }

    public ShowtimeResponseBody getShowtimeById(Long id) {
        return toShowtimeResponse(showtimeRepository.findShowtimeById(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public void takeSeat(Long id, int rowNum, int seatNum) {
        Showtime showtime = showtimeRepository.findShowtimeById(id);
        showtime.getSeats().forEach(seat -> {
                    if (seat.getRowNumber() == rowNum && seat.getSeatNumber() == seatNum) {
                        seat.setAvailable(false);
                    }
                }
        );
        showtimeRepository.save(showtime);
    }

    @Transactional(rollbackOn = Exception.class)
    public void returnSeat(Long id, int rowNum, int seatNum) {
        Showtime showtime  = showtimeRepository.findShowtimeById(id);
        showtime.getSeats().forEach(seat -> {
                    if (seat.getRowNumber() == rowNum && seat.getSeatNumber() == seatNum) {
                        seat.setAvailable(true);
                    }
                }
        );
        showtimeRepository.save(showtime);

    }

    public List<ShowtimeResponseBody> getAllShowtimeToday() {
        LocalDate today = LocalDate.now();
        List<Showtime> showtimes = showtimeRepository.findShowtimesByPremiereDate(today);

        return showtimes.stream()
                .map(ShowtimeService::toShowtimeResponse)
                .toList();
    }

    public static ShowtimeResponseBody toShowtimeResponse(Showtime showtime) {
        return ShowtimeResponseBody.builder()
                .id(showtime.getId())
                .hallName(showtime.getHallName())
                .title(showtime.getTitle())
                .genre(showtime.getGenre())
                .description(showtime.getDescription())
                .premiereDate(showtime.getPremiereDate())
                .startTime(showtime.getStartTime())
                .seats(showtime.getSeats())
                .build();
    }

}
