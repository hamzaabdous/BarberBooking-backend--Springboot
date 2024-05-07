package org.example.barbershopservice.web;

import org.example.barbershopservice.entities.Barbershop;
import org.example.barbershopservice.repsoitories.BarbershopRepository;
import org.example.barbershopservice.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class BarbershopRestControllers {
    private BarbershopRepository barbershopRepository;
    private UserRestClient userRestClient;

    public BarbershopRestControllers(BarbershopRepository barbershopRepository, UserRestClient userRestClient) {
        this.barbershopRepository = barbershopRepository;
        this.userRestClient = userRestClient;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/barbershops")
    public List<Barbershop> findAllOrders(){
        List<Barbershop> AllBarbershops = barbershopRepository.findAll();

        return AllBarbershops;
    }
}
