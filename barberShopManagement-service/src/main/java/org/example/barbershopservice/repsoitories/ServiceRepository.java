package org.example.barbershopservice.repsoitories;

import org.example.barbershopservice.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
