package com.musalasoft.eventbooking.dtos.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String statusCode;
    private String description;
    private LocalDate dateCreated;
}
