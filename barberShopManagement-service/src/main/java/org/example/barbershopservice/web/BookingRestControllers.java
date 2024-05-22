package org.example.barbershopservice.web;


import org.example.barbershopservice.entities.Booking;
import org.example.barbershopservice.entities.BookingStatus;
import org.example.barbershopservice.repsoitories.BookingRepository;
import org.example.barbershopservice.repsoitories.BookingStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingRestControllers {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingStatusRepository bookingStatusRepository;

    @GetMapping("/availability")
    // api url : GET /api/bookings/availability?barbershopId=1&startTime=2023-05-20T10:00:00&endTime=2023-05-20T11:00:00
    public boolean isBarbershopAvailable(@RequestParam Long barbershopId,
                                         @RequestParam LocalDateTime startTime,
                                         @RequestParam LocalDateTime endTime) {
        List<Booking> bookings = bookingRepository.findBookingsByBarbershopAndPeriod(barbershopId, startTime, endTime);
        return bookings.isEmpty();
    }
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        addBookingStatus(savedBooking, "CREATED", "System");
        return savedBooking;
    }

    @PutMapping
    public Booking updateBooking(@RequestBody Booking booking) {
        Booking updatedBooking = bookingRepository.save(booking);
        addBookingStatus(updatedBooking, "UPDATED", "System");
        return updatedBooking;
    }

    @DeleteMapping("/{bookingId}")
    public void cancelBooking(@PathVariable Long bookingId, @RequestParam String changedBy) {
        Booking booking = bookingRepository.findById(String.valueOf(bookingId)).orElseThrow(() -> new RuntimeException("Booking not found"));
        bookingRepository.delete(booking);
        addBookingStatus(booking, "CANCELLED", changedBy);
    }

    @GetMapping("/{bookingId}/statuses")
    public List<BookingStatus> getBookingStatus(@PathVariable Long bookingId) {
        Booking booking = bookingRepository.findById(String.valueOf(bookingId)).orElseThrow(() -> new RuntimeException("Booking not found"));
        return bookingStatusRepository.findByBooking(booking);
    }

    private void addBookingStatus(Booking booking, String status, String changedBy) {
        BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setBooking(booking);
        bookingStatus.setStatus(status);
        bookingStatus.setTimestamp(LocalDateTime.now());
        bookingStatus.setChangedBy(changedBy);
        bookingStatusRepository.save(bookingStatus);
    }
}
