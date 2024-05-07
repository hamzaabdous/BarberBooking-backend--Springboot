package org.example.barbershopservice.restclients;

import org.example.barbershopservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8089", name = "auth-service")
public interface UserRestClient {
    @GetMapping("/api/users")
    List<User> getAllUsers();
    @GetMapping("/api/users/{id}")
    User findUserById(@PathVariable String id);
}
