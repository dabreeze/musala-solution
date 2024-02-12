package com.musalasoft.eventbooking.controllers;

import com.musalasoft.eventbooking.dtos.requests.CreateUserRequest;
import com.musalasoft.eventbooking.dtos.response.AuthenticationResponse;
import com.musalasoft.eventbooking.models.AppUser;
import com.musalasoft.eventbooking.services.userService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@PreAuthorize("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/User", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody @Validated CreateUserRequest request){
        AuthenticationResponse crestedUser = userService.createUser(request);
        return  crestedUser!=null ? new ResponseEntity<>(null, HttpStatus.CREATED):
         new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//                : new ResponseEntity<>(userService.createUser(request),HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/health")
    public ResponseEntity<String> getHealth(){
        return new ResponseEntity<>("Service Running",HttpStatus.OK);
    }



}
