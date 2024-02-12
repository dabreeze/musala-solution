package com.musalasoft.eventbooking.controllers;

import com.musalasoft.eventbooking.dtos.requests.Credentials;
import com.musalasoft.eventbooking.dtos.response.AuthenticationResponse;
import com.musalasoft.eventbooking.dtos.response.LoginResponse;
import com.musalasoft.eventbooking.services.authService.AuthService;
import com.musalasoft.eventbooking.services.userService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private AuthService authService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginResponse> login(@RequestBody Credentials request){
        LoginResponse loggedInUser = userService.loginUser(request);
        return loggedInUser != null ? new ResponseEntity<>(loggedInUser, HttpStatus.OK)
                : new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);
    }
}
