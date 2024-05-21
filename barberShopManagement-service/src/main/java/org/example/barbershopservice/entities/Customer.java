package org.example.barbershopservice.entities;


import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Customer extends Person{
    private String loyaltyCardNumber;

}
