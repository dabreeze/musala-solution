package com.musalasoft.eventbooking.services.eventService;

import com.musalasoft.eventbooking.dtos.requests.TicketRequest;
import com.musalasoft.eventbooking.models.Category;
import com.musalasoft.eventbooking.models.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventsService {
    Event createEvent(Event request);
    List<Event>searchEventsWithFilters(String name, LocalDate startDate, LocalDate endDate, Category category);
    Optional<Event> findEventById(Long id);
    Long reserveTicket(Long eventId, TicketRequest request);
    Map<String,String> cancelReservation(Long eventId);
    List<Event>getBookedEvents();
    void sendNotifications();

}
