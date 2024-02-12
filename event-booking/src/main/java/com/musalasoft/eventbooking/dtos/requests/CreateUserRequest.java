package com.musalasoft.eventbooking.dtos.requests;

import lombok.*;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CreateUserRequest {
    @Size(max = 100, message = "max length exceeded")
    @NotEmpty(message = "please provide a name")
    @NotNull(message = "please provide a name")
    private String name;
    @Email(message = "Enter a valid email format")
    @NotEmpty(message = "please provide email")
    @NotNull(message = "please provide email")
    private String email;
    @NotEmpty(message = "please provide password")
    @NotNull(message = "please provide password")
    @Size(min = 8,message = "must be more than 8 characters long")
    private String password;
}
