package com.musalasoft.eventbooking.utils;

import com.musalasoft.eventbooking.dtos.requests.EventResponseDTO;
import com.musalasoft.eventbooking.dtos.response.SuccessResponse;
import com.musalasoft.eventbooking.dtos.response.UserResponse;
import com.musalasoft.eventbooking.models.Event;
import com.musalasoft.eventbooking.models.AppUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Utils {
    public SuccessResponse mapSavedEventToEventResponse(Event savedEvent){
        SuccessResponse response = new SuccessResponse();
        response.setResponseMsg("success");
        response.setName(savedEvent.getName());
        response.setEndDate(savedEvent.getEndDate());
        response.setDateCreated(savedEvent.getStartDate());
        return response;

    }

    public UserResponse mapSavedUserToResponse(AppUser savedAppUser){
        UserResponse response = new UserResponse();
        response.setDescription("Created");
        response.setStatusCode(String.valueOf(HttpStatus.CREATED.value()));
        response.setDateCreated(LocalDate.now());

        return response;
    }

    public List<EventResponseDTO> mapEventResponses(List<Event> event){
        return event.stream()
                .map(e->{
                    EventResponseDTO eventResponseDTO = new EventResponseDTO();
                    eventResponseDTO.setCategory(e.getCategory());
                    eventResponseDTO.setStartDate(e.getStartDate());
                    eventResponseDTO.setAvailableAttendeesCount(Integer.parseInt(e.getAvailableAttendeesCount()));
                    eventResponseDTO.setName(e.getName());
                    eventResponseDTO.setEndDate(e.getEndDate());
                    return eventResponseDTO;
                })
                .toList();
    }
}
