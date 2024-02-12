package com.musalasoft.eventbooking.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

    private String status;
    private String statusMessage;
}
