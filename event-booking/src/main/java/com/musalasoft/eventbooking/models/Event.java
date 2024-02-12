package com.musalasoft.eventbooking.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "cannot be blank or null")
    @NotBlank(message = "cannot be blank or empty")
    @Size(max = 100, message = "cannot be more than 100 charaters")
    private String name;
    @DateTimeFormat(pattern = "yy-MM-dd")
    private LocalDate startDate;
    //    @NonNull
    @DateTimeFormat(pattern = "yy-MM-dd")
    private LocalDate endDate;
    @Size(max = 1000, message = "maximum number of characters exceeded")
    @NotBlank(message = "cannot be blank or empty")
    @NotNull(message = "cannot be blank or null")
    private String availableAttendeesCount;
    private String eligibleAttendeesCount;
    @Size(max = 500)
    private String description;
    private Long userId;
    @OneToMany
    private List<Ticket> ticketId;
    //    @NotBlank(message = "Cannot be empty please specify:  CONCERT, CONFERENCE or GAME")
    @Enumerated(EnumType.STRING)
    private Category category;

}
