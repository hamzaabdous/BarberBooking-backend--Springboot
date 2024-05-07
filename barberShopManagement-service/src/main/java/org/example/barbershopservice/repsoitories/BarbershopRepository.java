package org.example.barbershopservice.repsoitories;

import org.example.barbershopservice.entities.Barbershop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbershopRepository extends JpaRepository<Barbershop, String> {
}
