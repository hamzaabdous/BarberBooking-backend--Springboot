package org.example.barbershopservice.repsoitories;

import org.example.barbershopservice.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, String> {
}
