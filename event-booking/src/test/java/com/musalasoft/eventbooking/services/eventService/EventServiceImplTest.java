package com.musalasoft.eventbooking.services.eventService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musalasoft.eventbooking.dtos.requests.TicketRequest;
import com.musalasoft.eventbooking.models.Category;
import com.musalasoft.eventbooking.models.Event;
import com.musalasoft.eventbooking.models.Ticket;
import com.musalasoft.eventbooking.repository.EventRepository;
import com.musalasoft.eventbooking.repository.TicketRepository;
import com.musalasoft.eventbooking.repository.UserRepository;
import com.musalasoft.eventbooking.services.ticket.TicketService;
import com.musalasoft.eventbooking.utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EventServiceImpl.class})
@ExtendWith(SpringExtension.class)
class EventServiceImplTest {
    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private EventServiceImpl eventServiceImpl;

    @MockBean
    private TicketRepository ticketRepository;

    @MockBean
    private TicketService ticketService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Utils utils;

    @Test
    void testCreateEvent() {
        Event event = new Event();
        event.setAvailableAttendeesCount("3");
        event.setCategory(Category.CONCERT);
        event.setDescription("The characteristics of someone or something");
        event.setEligibleAttendeesCount("3");
        event.setEndDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");
        event.setStartDate(LocalDate.of(1970, 1, 1));
        event.setTicketId(new ArrayList<>());
        event.setUserId(1L);
        when(eventRepository.save(Mockito.<Event>any())).thenReturn(event);

        Event createRequest = new Event();
        createRequest.setAvailableAttendeesCount("3");
        createRequest.setCategory(Category.CONCERT);
        createRequest.setDescription("The characteristics of someone or something");
        createRequest.setEligibleAttendeesCount("3");
        createRequest.setEndDate(LocalDate.of(1970, 1, 1));
        createRequest.setId(1L);
        createRequest.setName("Name");
        createRequest.setStartDate(LocalDate.of(1970, 1, 1));
        createRequest.setTicketId(new ArrayList<>());
        createRequest.setUserId(1L);
        Event actualCreateEventResult = eventServiceImpl.createEvent(createRequest);
        verify(eventRepository).save(Mockito.<Event>any());
        assertSame(event, actualCreateEventResult);
    }

    @Test
    void testSearchEventsWithFilters() {
        ArrayList<Event> eventList = new ArrayList<>();
        when(eventRepository.findByFilters(Mockito.<String>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any(),
                Mockito.<Category>any())).thenReturn(eventList);
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        List<Event> actualSearchEventsWithFiltersResult = eventServiceImpl.searchEventsWithFilters("Name", startDate,
                LocalDate.of(1970, 1, 1), Category.CONCERT);
        verify(eventRepository).findByFilters(Mockito.<String>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any(),
                Mockito.<Category>any());
        assertTrue(actualSearchEventsWithFiltersResult.isEmpty());
        assertSame(eventList, actualSearchEventsWithFiltersResult);
    }

    @Test
    void testSearchEventsWithFilters2() {
        ArrayList<Event> eventList = new ArrayList<>();
        when(eventRepository.findByFilters(Mockito.<String>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any(),
                Mockito.<Category>any())).thenReturn(eventList);
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        List<Event> actualSearchEventsWithFiltersResult = eventServiceImpl.searchEventsWithFilters("Name", startDate,
                LocalDate.of(1970, 1, 1), Category.CONFERENCE);
        verify(eventRepository).findByFilters(Mockito.<String>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any(),
                Mockito.<Category>any());
        assertTrue(actualSearchEventsWithFiltersResult.isEmpty());
        assertSame(eventList, actualSearchEventsWithFiltersResult);
    }

    @Test
    void testSearchEventsWithFilters3() {
        ArrayList<Event> eventList = new ArrayList<>();
        when(eventRepository.findByFilters(Mockito.<String>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any(),
                Mockito.<Category>any())).thenReturn(eventList);
        LocalDate startDate = LocalDate.of(1970, 1, 1);
        List<Event> actualSearchEventsWithFiltersResult = eventServiceImpl.searchEventsWithFilters("Name", startDate,
                LocalDate.of(1970, 1, 1), Category.GAME);
        verify(eventRepository).findByFilters(Mockito.<String>any(), Mockito.<LocalDate>any(), Mockito.<LocalDate>any(),
                Mockito.<Category>any());
        assertTrue(actualSearchEventsWithFiltersResult.isEmpty());
        assertSame(eventList, actualSearchEventsWithFiltersResult);
    }

    /**
     * Method under test: {@link EventServiceImpl#getBookedEvents()}
     */
    @Test
    void testGetBookedEvents() {
        ArrayList<Event> eventList = new ArrayList<>();
        when(eventRepository.findAll()).thenReturn(eventList);
        List<Event> actualBookedEvents = eventServiceImpl.getBookedEvents();
        verify(eventRepository).findAll();
        assertTrue(actualBookedEvents.isEmpty());
        assertSame(eventList, actualBookedEvents);
    }

    @Test
    void testCancelReservation() {
        Event event = new Event();
        event.setAvailableAttendeesCount("3");
        event.setCategory(Category.CONCERT);
        event.setDescription("The characteristics of someone or something");
        event.setEligibleAttendeesCount("3");
        event.setEndDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");
        event.setStartDate(LocalDate.of(1970, 1, 1));
        event.setTicketId(new ArrayList<>());
        event.setUserId(1L);
        Optional<Event> ofResult = Optional.of(event);
        doNothing().when(eventRepository).deleteById(Mockito.<Long>any());
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Map<String, String> actualCancelReservationResult = eventServiceImpl.cancelReservation(1L);
        verify(eventRepository).deleteById(Mockito.<Long>any());
        verify(eventRepository).findById(Mockito.<Long>any());
        assertEquals("Event deleted", actualCancelReservationResult.get("message"));
        assertEquals(1, actualCancelReservationResult.size());
    }

    @Test
    void testCancelReservation2() {
        Optional<Event> emptyResult = Optional.empty();
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        Map<String, String> actualCancelReservationResult = eventServiceImpl.cancelReservation(1L);
        verify(eventRepository).findById(Mockito.<Long>any());
        assertTrue(actualCancelReservationResult.isEmpty());
    }

    @Test
    void testFindEventById() {
        Event event = new Event();
        event.setAvailableAttendeesCount("3");
        event.setCategory(Category.CONCERT);
        event.setDescription("The characteristics of someone or something");
        event.setEligibleAttendeesCount("3");
        event.setEndDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");
        event.setStartDate(LocalDate.of(1970, 1, 1));
        event.setTicketId(new ArrayList<>());
        event.setUserId(1L);
        Optional<Event> ofResult = Optional.of(event);
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<Event> actualFindEventByIdResult = eventServiceImpl.findEventById(1L);
        verify(eventRepository).findById(Mockito.<Long>any());
        assertTrue(actualFindEventByIdResult.isPresent());
    }

    @Test
    void testReserveTicket() {
        Event event = new Event();
        event.setAvailableAttendeesCount("3");
        event.setCategory(Category.CONCERT);
        event.setDescription("The characteristics of someone or something");
        event.setEligibleAttendeesCount("3");
        event.setEndDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");
        event.setStartDate(LocalDate.of(1970, 1, 1));
        event.setTicketId(new ArrayList<>());
        event.setUserId(1L);
        Optional<Event> ofResult = Optional.of(event);

        Event event2 = new Event();
        event2.setAvailableAttendeesCount("3");
        event2.setCategory(Category.CONCERT);
        event2.setDescription("The characteristics of someone or something");
        event2.setEligibleAttendeesCount("3");
        event2.setEndDate(LocalDate.of(1970, 1, 1));
        event2.setId(1L);
        event2.setName("Name");
        event2.setStartDate(LocalDate.of(1970, 1, 1));
        event2.setTicketId(new ArrayList<>());
        event2.setUserId(1L);
        when(eventRepository.save(Mockito.<Event>any())).thenReturn(event2);
        when(eventRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Ticket ticket = new Ticket();
        ticket.setEventId("42");
        ticket.setExpiryDate(LocalDate.of(1970, 1, 1));
        ticket.setId(1L);
        ticket.setTimeCreated(LocalDate.of(1970, 1, 1));
        ticket.setValid(true);
        when(ticketService.reserveTicket(Mockito.<TicketRequest>any())).thenReturn(ticket);

        TicketRequest attendeesCount = new TicketRequest();
        attendeesCount.setAttendeesCount("3");
        attendeesCount.setEventId(1L);
        Long actualReserveTicketResult = eventServiceImpl.reserveTicket(1L, attendeesCount);
        verify(ticketService).reserveTicket(Mockito.<TicketRequest>any());
        verify(eventRepository).findById(Mockito.<Long>any());
        verify(eventRepository).save(Mockito.<Event>any());
        assertEquals(1L, actualReserveTicketResult.longValue());
    }

    @Test
    void testSendNotifications() {
        when(eventRepository.findAll()).thenReturn(new ArrayList<>());
        eventServiceImpl.sendNotifications();
        verify(eventRepository).findAll();
    }

    @Test
    void testSendNotifications2() {
        Event event = new Event();
        event.setAvailableAttendeesCount("3");
        event.setCategory(Category.CONCERT);
        event.setDescription("The characteristics of someone or something");
        event.setEligibleAttendeesCount("3");
        event.setEndDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");
        event.setStartDate(LocalDate.of(1970, 1, 1));
        event.setTicketId(new ArrayList<>());
        event.setUserId(1L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event);
        when(eventRepository.findAll()).thenReturn(eventList);
        eventServiceImpl.sendNotifications();
        verify(eventRepository).findAll();
    }

    @Test
    void testSendNotifications3() {
        Event event = new Event();
        event.setAvailableAttendeesCount("3");
        event.setCategory(Category.CONCERT);
        event.setDescription("The characteristics of someone or something");
        event.setEligibleAttendeesCount("3");
        event.setEndDate(LocalDate.of(1970, 1, 1));
        event.setId(1L);
        event.setName("Name");
        event.setStartDate(LocalDate.of(1970, 1, 1));
        event.setTicketId(new ArrayList<>());
        event.setUserId(1L);

        Event event2 = new Event();
        event2.setAvailableAttendeesCount("Available Attendees Count");
        event2.setCategory(Category.CONFERENCE);
        event2.setDescription("Description");
        event2.setEligibleAttendeesCount("Eligible Attendees Count");
        event2.setEndDate(LocalDate.of(1970, 1, 1));
        event2.setId(2L);
        event2.setName("42");
        event2.setStartDate(LocalDate.of(1970, 1, 1));
        event2.setTicketId(new ArrayList<>());
        event2.setUserId(2L);

        ArrayList<Event> eventList = new ArrayList<>();
        eventList.add(event2);
        eventList.add(event);
        when(eventRepository.findAll()).thenReturn(eventList);
        eventServiceImpl.sendNotifications();
        verify(eventRepository).findAll();
    }
}

