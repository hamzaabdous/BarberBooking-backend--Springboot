package org.example.barbershopservice.repsoitories;

import feign.Param;
import org.example.barbershopservice.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {

    @Query("SELECT b FROM Booking b WHERE b.barbershop.id = :barbershopId AND " +
            "((b.startTime < :endTime AND b.endTime > :startTime) OR " +
            "(b.startTime >= :startTime AND b.startTime < :endTime))")
    List<Booking> findBookingsByBarbershopAndPeriod(@Param("barbershopId") Long barbershopId,
                                                    @Param("startTime") LocalDateTime startTime,
                                                    @Param("endTime") LocalDateTime endTime);

}
