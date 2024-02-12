package com.musalasoft.eventbooking.dtos.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TicketRequest {
    private Long eventId;
    private String attendeesCount;
}
