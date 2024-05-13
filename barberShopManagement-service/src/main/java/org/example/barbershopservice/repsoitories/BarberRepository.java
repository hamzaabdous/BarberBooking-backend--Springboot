package org.example.barbershopservice.repsoitories;

import org.example.barbershopservice.entities.Barber;
import org.example.barbershopservice.entities.Barbershop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarberRepository extends JpaRepository<Barber, String> {
    List<Barber> findByBarbershopId(Long id);
}
