package com.umarov.bookingservice.service;

import com.umarov.bookingservice.clients.ShowtimeServiceClient;
import com.umarov.bookingservice.clients.UserServiceClient;
import com.umarov.bookingservice.dto.BookingAddRequest;
import com.umarov.bookingservice.dto.BookingResponse;
import com.umarov.bookingservice.dto.ShowtimeResponseBody;
import com.umarov.bookingservice.dto.UserResponse;
import com.umarov.bookingservice.entity.Booking;
import com.umarov.bookingservice.entity.Seat;
import com.umarov.bookingservice.repository.BookingRepository;
import com.umarov.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserServiceClient userServiceClient;
    private final ShowtimeServiceClient showtimeServiceClient;

    @Transactional(rollbackOn = Exception.class)
    public String addBooking(BookingAddRequest request) {
        UserResponse user = userServiceClient.getUserByID(request.getUserId());
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }

        ShowtimeResponseBody showtime = showtimeServiceClient.getShowtimeById(request.getShowtimeId());

        Optional<Seat> seatOptional = showtime.getSeats().stream()
                .filter(seat -> seat.equals(new Seat(request.getRowNumber(), request.getSeatNumber())))
                .findFirst();

        if (seatOptional.isPresent()) {
            Seat seat = seatOptional.get();
            if (seat.isAvailable()) {
                seat.setAvailable(false);
            } else {
                throw new RuntimeException("Seat is not available");
            }
        } else {
            throw new RuntimeException("Seat not found");
        }

        Booking booking = new Booking();
        booking.setUserId(user.getId());
        booking.setShowtimeId(showtime.getId());
        booking.setSeat(request.getRowNumber() + ":" + request.getSeatNumber());
        UUID uuid = UUID.randomUUID();
        booking.setPaymentId(uuid.toString());
        showtimeServiceClient.takeSeat(showtime.getId(), request.getRowNumber(), request.getSeatNumber());
        bookingRepository.save(booking);
        return uuid.toString();
    }

    @Transactional(rollbackOn = Exception.class)
    public void payForBooking(String paymentId) throws InterruptedException {
        Booking booking = bookingRepository.findBookingByPaymentId(paymentId);
        if (booking == null) {
            throw new RuntimeException("Booking not found");
        }
        Thread.sleep(2000);
        booking.setPaid(true);
    }

    public List<BookingResponse> getUserBookings(Long id) {
        List<Booking> bookings = bookingRepository.getBookingsByUserId(id);
        return bookings.stream().map(
                BookingService::toBookingResponse
        ).toList();
    }

    private static BookingResponse toBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .bookingId(booking.getId())
                .showtimeId(booking.getShowtimeId())
                .seat(booking.getSeat())
                .cancelled(booking.isCancelled())
                .paid(booking.isPaid())
                .build();
    }

    @Transactional(rollbackOn = Exception.class)
    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.getBookingsById(id);
        booking.setCancelled(true);
        String[] data = booking.getSeat().split(":");
        showtimeServiceClient.returnSeat(booking.getShowtimeId(), Integer.parseInt(data[0]),Integer.parseInt(data[1]));
        bookingRepository.save(booking);
    }
}
