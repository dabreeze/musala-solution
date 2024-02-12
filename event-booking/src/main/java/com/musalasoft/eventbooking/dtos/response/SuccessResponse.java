package com.musalasoft.eventbooking.dtos.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter @Setter
@NoArgsConstructor
public class SuccessResponse {
    private String name;
    private String responseMsg;

    public SuccessResponse(String name, String responseMsg, LocalDate dateCreated, LocalDate endDate) {
        this.name = name;
        this.responseMsg = responseMsg;
        this.dateCreated = dateCreated;
        this.endDate = endDate;
    }

    private LocalDate dateCreated;
    private LocalDate endDate;

}
