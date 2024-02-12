package com.musalasoft.eventbooking.dtos.requests;

import com.musalasoft.eventbooking.models.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter @Setter
public class CreateEventRequest {
    @NotNull(message="Cannot be null")
    @NotEmpty(message="Cannot be empty")
    @Size(min=100)
    private String name;
    @DateTimeFormat(pattern = "yy-MM-dd")
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private LocalDate date;

    @Size(min = 1000)
    @NotNull(message = "Cannot be null")
    @NotEmpty(message = "Cannot be empty")
    private Integer attendeesCount;
    @Size(min = 500)
    @NotNull(message = "Cannot be null please specify:  CONCERT, CONFERENCE or GAME")
    @NotEmpty(message = "Cannot be empty please specify:  CONCERT, CONFERENCE or GAME")
    private String description;
    @Enumerated(EnumType.STRING)
    private Category categor;
}
