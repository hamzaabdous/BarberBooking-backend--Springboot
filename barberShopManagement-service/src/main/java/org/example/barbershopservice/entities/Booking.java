package org.example.barbershopservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.barbershopservice.model.User;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Table(name = "Bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @Transient
    private User user;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    private LocalDateTime bookingTime;
    private String status; // Consider using an Enum (PENDING, CONFIRMED, COMPLETED, CANCELLED)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
