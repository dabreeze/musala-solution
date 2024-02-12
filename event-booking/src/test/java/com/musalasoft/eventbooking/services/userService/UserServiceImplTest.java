package com.musalasoft.eventbooking.services.userService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musalasoft.eventbooking.config.JwtService;
import com.musalasoft.eventbooking.dtos.requests.Credentials;
import com.musalasoft.eventbooking.dtos.response.LoginResponse;
import com.musalasoft.eventbooking.models.AppUser;
import com.musalasoft.eventbooking.models.Role;
import com.musalasoft.eventbooking.repository.UserRepository;
import com.musalasoft.eventbooking.utils.Utils;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private Utils utils;

    @Test
    void testLoginUser() throws AuthenticationException {
        AppUser appUser = new AppUser();
        appUser.setEmail("jane.doe@example.org");
        appUser.setId(1L);
        appUser.setName("Name");
        appUser.setPassword("iloveyou");
        appUser.setRole(Role.USER);
        Optional<AppUser> ofResult = Optional.of(appUser);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        LoginResponse actualLoginUserResult = userServiceImpl
                .loginUser(new Credentials("jane.doe@example.org", "iloveyou"));
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
        assertEquals("ABC123", actualLoginUserResult.getToken());
    }

    @Test
    void testLoginUser2() {
        Optional<AppUser> emptyResult = Optional.empty();
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        LoginResponse actualLoginUserResult = userServiceImpl
                .loginUser(new Credentials("jane.doe@example.org", "iloveyou"));
        verify(userRepository).findByEmail(Mockito.<String>any());
        assertNull(actualLoginUserResult.getToken());
    }
}

