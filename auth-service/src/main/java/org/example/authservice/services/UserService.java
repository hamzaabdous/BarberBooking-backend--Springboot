package org.example.authservice.services;


import org.example.authservice.dtos.UserRequest;
import org.example.authservice.dtos.UserResponse;

import java.util.List;


public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    UserResponse getUser();

    List<UserResponse> getAllUser();


}
