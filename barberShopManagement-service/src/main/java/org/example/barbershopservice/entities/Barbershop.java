package org.example.barbershopservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.barbershopservice.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder @ToString
@Table(name = "Barbershops")
public class Barbershop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Transient
    private List<User> owner;
    private String name;
    private String description;
    private String location; // Consider more complex types for real applications
    private String operationalHours; // JSON or String; consider a more structured approach
    private String contactEmail;
    private String contactPhoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}