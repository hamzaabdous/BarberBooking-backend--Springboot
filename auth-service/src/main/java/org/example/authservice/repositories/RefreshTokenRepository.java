package org.example.authservice.repositories;


import org.example.authservice.helpers.RefreshableCRUDRepository;
import org.example.authservice.models.RefreshToken;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RefreshTokenRepository extends RefreshableCRUDRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByToken(String token);
}
