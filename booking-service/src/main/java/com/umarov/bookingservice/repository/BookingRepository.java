package com.umarov.bookingservice.repository;

import com.umarov.bookingservice.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findBookingByPaymentId(String paymentId);

    List<Booking> getBookingsByUserId(Long userId);

    Booking getBookingsById(Long id);
}
