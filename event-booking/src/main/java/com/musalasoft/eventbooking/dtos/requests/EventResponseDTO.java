package com.musalasoft.eventbooking.dtos.requests;

import com.musalasoft.eventbooking.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class EventResponseDTO {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int availableAttendeesCount;
    private String description;

    private Category category;
}
