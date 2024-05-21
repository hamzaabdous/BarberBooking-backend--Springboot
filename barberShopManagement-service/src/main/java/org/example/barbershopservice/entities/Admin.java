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
public class Admin extends Person {
    private String role;

    @OneToOne
    @JoinColumn(name = "login_id")
    private Login login;

}