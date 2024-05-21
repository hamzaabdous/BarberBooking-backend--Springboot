package org.example.barbershopservice.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "Barbers")
public class Barber extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String expertise;
    private int yearsOfExperience;

    @ManyToOne
    @JoinColumn(name = "barbershop_id")
    private Barbershop barbershop;

    @OneToOne
    @JoinColumn(name = "login_id")
    private Login login;
}
