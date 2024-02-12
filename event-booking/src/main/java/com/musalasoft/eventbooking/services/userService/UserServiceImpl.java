package com.musalasoft.eventbooking.services.userService;

import com.musalasoft.eventbooking.config.JwtService;
import com.musalasoft.eventbooking.dtos.requests.CreateUserRequest;
import com.musalasoft.eventbooking.dtos.requests.Credentials;
import com.musalasoft.eventbooking.dtos.response.AuthenticationResponse;
import com.musalasoft.eventbooking.dtos.response.LoginResponse;
import com.musalasoft.eventbooking.models.Role;
import com.musalasoft.eventbooking.models.AppUser;
import com.musalasoft.eventbooking.repository.UserRepository;
import com.musalasoft.eventbooking.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Utils utils;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtHelper;


    @Override
    public LoginResponse loginUser(Credentials request) {

        var user = userRepository.findByEmail(request.getEmail());

        if(user.isEmpty()) {
            return LoginResponse
                    .builder()
                    .build();
        }
        String jwtToken = null;

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            jwtToken = jwtHelper.generateToken(user.get());

            return LoginResponse
                    .builder()
                    .token(jwtToken).build();
        }catch (Exception ex){
            ex.getMessage();
        }
        return null;
    }

    @Override
    public AuthenticationResponse createUser(CreateUserRequest request) {
        Optional<AppUser> foundUser = userRepository.findByEmail(request.getEmail());
        if(foundUser.isPresent()){
            return AuthenticationResponse
                    .builder()
                    .statusCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .description("This User already exist")
                    .build();
        }

        var newUser = AppUser
                .builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(newUser);

        return AuthenticationResponse.builder()
                .statusCode("201")
                .build();
    }




}
