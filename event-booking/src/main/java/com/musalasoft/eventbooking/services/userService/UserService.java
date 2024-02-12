package com.musalasoft.eventbooking.services.userService;

import com.musalasoft.eventbooking.dtos.requests.CreateUserRequest;
import com.musalasoft.eventbooking.dtos.requests.Credentials;
import com.musalasoft.eventbooking.dtos.response.AuthenticationResponse;
import com.musalasoft.eventbooking.dtos.response.LoginResponse;

public interface  UserService {
    LoginResponse loginUser(Credentials request);
    AuthenticationResponse createUser(CreateUserRequest request);
}
