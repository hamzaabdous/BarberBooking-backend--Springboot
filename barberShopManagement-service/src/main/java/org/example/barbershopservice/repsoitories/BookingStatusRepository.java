package org.example.barbershopservice.repsoitories;

import org.example.barbershopservice.entities.Booking;
import org.example.barbershopservice.entities.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingStatusRepository extends JpaRepository<BookingStatus, Long> {
    List<BookingStatus> findByBooking(Booking booking);
}