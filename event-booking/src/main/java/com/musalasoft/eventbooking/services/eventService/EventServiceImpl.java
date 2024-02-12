package com.musalasoft.eventbooking.services.eventService;

import com.musalasoft.eventbooking.dtos.requests.TicketRequest;
import com.musalasoft.eventbooking.models.Category;
import com.musalasoft.eventbooking.models.Event;
import com.musalasoft.eventbooking.models.AppUser;
import com.musalasoft.eventbooking.models.Ticket;
import com.musalasoft.eventbooking.repository.EventRepository;
import com.musalasoft.eventbooking.repository.TicketRepository;
import com.musalasoft.eventbooking.repository.UserRepository;
import com.musalasoft.eventbooking.services.ticket.TicketService;
import com.musalasoft.eventbooking.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class EventServiceImpl implements EventsService {
    private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);


    private final EventRepository eventRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TicketService ticketService;

    private final Utils utils;

    public Event createEvent( Event createRequest) {
        logger.info("Got event: Saving Event");
        return eventRepository.save(createRequest);
    }

    public List<Event> searchEventsWithFilters(String name, LocalDate startDate, LocalDate endDate, Category category) {
        return eventRepository.findByFilters(name, startDate, endDate, category);
    }

    public List<Event> getBookedEvents() {
        return eventRepository.findAll();


    }

    public Map<String,String> cancelReservation(Long event) {
           Optional<Event> foundEvent = eventRepository.findById(event);
        logger.info("Got event: found user {}" , event);
        Map<String,String>result = new HashMap<>();

        if (foundEvent.isPresent()) {
                eventRepository.deleteById(event);
                result.put("message","Event deleted");
                return result;
        }
        return result;
    }

    public Optional<Event> findEventById(Long id) {
        return Optional.ofNullable(eventRepository.findById(id).orElseThrow(
                () -> new NullPointerException("No event found")));

    }

    @Override
    public Long reserveTicket(Long eventId, TicketRequest attendeesCount) {

        Optional<Event> foundEvent = eventRepository.findById(eventId);
        if (foundEvent.isEmpty()) {
            throw new NullPointerException("No event Found");
        }

        TicketRequest request = new TicketRequest();
        request.setEventId(eventId);
        request.setAttendeesCount(attendeesCount.getAttendeesCount());
        Ticket ticketId = ticketService.reserveTicket(request);
        Event newEvent = foundEvent.get();


        List<Ticket> ticketIds = foundEvent.get().getTicketId();
        if (ticketIds != null) {

            ticketIds.add(ticketId);
        } else {
            ticketIds = List.of(ticketId);
        }
        newEvent.setTicketId(ticketIds);
        eventRepository.save(newEvent);
        return ticketId.getId();

    }

    @Scheduled(cron = "0 * * * * ?")
    @Override
    public void sendNotifications() {
        List<Event>upcomingEvents = eventRepository.findAll().stream().filter(event -> event.getStartDate().isAfter(LocalDate.now()))
                .toList();
        for (Event event : upcomingEvents) {
            if (event.getTicketId() == null) {
                return;
            }
            Long userID = event.getUserId();
            Optional<AppUser> user = userRepository.findById(userID);


            if (user.isPresent()) {
                logger.info("Found user ");
                logger.info("Get ready for you event {} starting soon on {} :",user.get().getName(),event.getStartDate().getDayOfWeek());
            } else {
                logger.info("No ticket found");

            }

        }
    }


}
