package com.musalasoft.eventbooking.dtos.requests;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Credentials {
    @Email
    private String email;
    @Size(min = 8,message = "must be more than 8 characters long")
    private String password;
}
