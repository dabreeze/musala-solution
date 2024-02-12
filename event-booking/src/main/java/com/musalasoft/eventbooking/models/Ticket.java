package com.musalasoft.eventbooking.models;

import lombok.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate timeCreated;
    private LocalDate expiryDate;

    private String eventId;
    private boolean isValid;

}
