package org.example.barbershopservice.entities;

import jakarta.persistence.*;

@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne(mappedBy = "login")
    private Admin admin;

    @OneToOne(mappedBy = "login")
    private Barber barber;

    // Getters and Setters
}