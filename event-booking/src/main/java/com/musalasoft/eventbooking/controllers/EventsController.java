package com.musalasoft.eventbooking.controllers;

import com.musalasoft.eventbooking.dtos.requests.TicketRequest;
import com.musalasoft.eventbooking.models.Category;
import com.musalasoft.eventbooking.models.Event;
import com.musalasoft.eventbooking.repository.EventRepository;
import com.musalasoft.eventbooking.services.eventService.EventsService;
import com.musalasoft.eventbooking.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventsController {
    //
    private final EventsService eventService;
    private final Utils utils;
    private final EventRepository eventRepository;

    public EventsController(EventsService eventService, Utils utils,
                            EventRepository eventRepository) {
        this.eventService = eventService;
        this.utils = utils;
        this.eventRepository = eventRepository;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,String>> createEvent(@RequestBody @Validated Event createEventRequest) {
        Event createdEvent = eventService.createEvent(createEventRequest);
        Map<String,String> event = new HashMap<>();
        event.put("eventId", String.valueOf(createdEvent.getId()));
        if(createdEvent != null ){
            return new ResponseEntity<>(event, HttpStatus.CREATED);
        } else  {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping()
    public ResponseEntity<List<Event>> getEvents(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) LocalDate startDate,
            @RequestParam(required = false) LocalDate endDate,
            @RequestParam(required = false) Category category) {

        List<Event> events = new ArrayList<>();

        if (name != null) {
            events = eventRepository.findByNameContainingIgnoreCase(name);
        }
        else if (category != null) {
            events = eventRepository.findByCategory(category);
        }
        else if (startDate != null) {
            events = eventRepository.findByCategory(category);
        }else {
            events = eventRepository.findAll();
        }

        return !events.isEmpty() ? new ResponseEntity<>(events, HttpStatus.OK)
                : new ResponseEntity<>(events, HttpStatus.BAD_REQUEST);

    }


    @GetMapping(path = "/booked-events")
    public ResponseEntity<List<Event>> getBookedEvents() {
        List<Event> bookedEvents = eventService.getBookedEvents();
        return new ResponseEntity<>(bookedEvents, HttpStatus.OK);
    }

    @PostMapping("/{eventId}/tickets")
    public ResponseEntity<Long> reserveTicket(@PathVariable Long eventId, @RequestBody TicketRequest request) {
        Long reservedEvent = eventService.reserveTicket(eventId,request);
        return reservedEvent != null ?new ResponseEntity<>(reservedEvent, HttpStatus.CREATED):
        new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/notify")
    public void notifyUser(){
        eventService.sendNotifications();
    }

    @GetMapping("/{eventId}/cancel")
    public ResponseEntity<Object> notifyUser(@PathVariable Long eventId){
        return new ResponseEntity<>(eventService.cancelReservation(eventId),HttpStatus.OK);
    }
}
