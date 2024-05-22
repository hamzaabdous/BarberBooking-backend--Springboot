package org.example.barbershopservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.example.barbershopservice.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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


    private LocalDateTime bookingTime;
    private String status; // Consider using an Enum (PENDING, CONFIRMED, COMPLETED, CANCELLED)
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;


    @ManyToOne
    @JoinColumn(name = "barbershop_id")
    private Barbershop barbershop;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BookingStatus> statuses = new HashSet<>();
}
