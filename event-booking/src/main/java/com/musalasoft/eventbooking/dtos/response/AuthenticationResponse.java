package com.musalasoft.eventbooking.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthenticationResponse {
    private String description;
    private String statusCode;
    private String token;
}
