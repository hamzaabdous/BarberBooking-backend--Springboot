package org.example.barbershopservice.web;

import jakarta.transaction.Transactional;
import org.example.barbershopservice.entities.Barber;
import org.example.barbershopservice.entities.Barbershop;
import org.example.barbershopservice.entities.Service;
import org.example.barbershopservice.repsoitories.BarberRepository;
import org.example.barbershopservice.repsoitories.BarbershopRepository;
import org.example.barbershopservice.repsoitories.ServiceRepository;
import org.example.barbershopservice.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/barbershops")

public class BarbershopRestControllers {
    private BarbershopRepository barbershopRepository;
    private BarberRepository barberRepository;
    private ServiceRepository serviceRepository;

    private UserRestClient userRestClient;

    public BarbershopRestControllers(BarbershopRepository barbershopRepository, UserRestClient userRestClient,ServiceRepository serviceRepository) {
        this.barbershopRepository = barbershopRepository;
        this.userRestClient = userRestClient;
        this.serviceRepository = serviceRepository;

    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/")
    public List<Barbershop> findAllOrders(){
        List<Barbershop> AllBarbershops = barbershopRepository.findAll();

        return AllBarbershops;
    }

    @PostMapping("/create")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Barbershop> createBarbershop(@RequestBody Barbershop barbershop) {
        // Ensure services reference this barbershop
        barbershop.getServices().forEach(service -> service.setBarbershop(barbershop));

        // Save the barbershop along with its services
        Barbershop savedBarbershop = barbershopRepository.save(barbershop);

        return new ResponseEntity<>(savedBarbershop, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Barbershop> getBarbershopById(@PathVariable Long id) {
        return barbershopRepository.findById(String.valueOf(id))
                .map(ResponseEntity::ok) // Return 200 OK with the barbershop
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 Not Found if the barbershop doesn't exist
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Barbershop> updateBarbershop(@PathVariable Long id, @RequestBody Barbershop barbershopDetails) {
        return barbershopRepository.findById(String.valueOf(id))
                .map(existingBarbershop -> {
                    existingBarbershop.setName(barbershopDetails.getName());
                    existingBarbershop.setLocation(barbershopDetails.getLocation());
                    existingBarbershop.setServices(barbershopDetails.getServices());
                    barbershopRepository.save(existingBarbershop);
                    return ResponseEntity.ok(existingBarbershop); // Return 200 OK with the updated barbershop
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 Not Found if the barbershop doesn't exist
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteBarbershop(@PathVariable Long id) {
        return barbershopRepository.findById(String.valueOf(id))
                .map(barbershop -> {
                    barbershopRepository.delete(barbershop);
                    return ResponseEntity.ok().build(); // Return 200 OK with empty body to indicate successful deletion
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 Not Found if the barbershop doesn't exist
    }

    @GetMapping("/{id}/barbers")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Barber>> getBarbersByBarbershop(@PathVariable Long id) {
        if (!barbershopRepository.existsById(String.valueOf(id))) {
            return ResponseEntity.notFound().build();  // Return 404 Not Found if the barbershop doesn't exist
        }
        List<Barber> barbers = barberRepository.findByBarbershopId(id);
        return ResponseEntity.ok(barbers);  // Return 200 OK with the list of barbers
    }

    @GetMapping("/by-service/{serviceName}")
    public List<Barbershop> findBarbershopsByServiceName(String serviceName) {
        List<Service> services = serviceRepository.findByName(serviceName);
        return services.stream()
                .map(Service::getBarbershop)
                .distinct()
                .collect(Collectors.toList());
    }

    @PostMapping("/{barbershopId}/barbers")
    public Barber addBarberToBarbershop(long barbershopId, Barber barber) {
        Barbershop barbershop = barbershopRepository.findById(String.valueOf(barbershopId))
                .orElseThrow(() -> new RuntimeException("Barbershop not found"));

        barber.setBarbershop(barbershop);
        return barberRepository.save(barber);
    }
}
