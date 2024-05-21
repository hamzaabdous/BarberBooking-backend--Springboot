package org.example.barbershopservice.repsoitories;

import org.example.barbershopservice.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, String> {
    List<Service> findByName(String serviceName);
}
