package com.musalasoft.eventbooking.services.ticket;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.musalasoft.eventbooking.dtos.requests.TicketRequest;
import com.musalasoft.eventbooking.models.Ticket;
import com.musalasoft.eventbooking.repository.TicketRepository;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TicketServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TicketServiceImplTest {
    @MockBean
    private TicketRepository ticketRepository;

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @Test
    void testReserveTicket() {
        Ticket ticket = new Ticket();
        ticket.setEventId("42");
        ticket.setExpiryDate(LocalDate.of(1970, 1, 1));
        ticket.setId(1L);
        ticket.setTimeCreated(LocalDate.of(1970, 1, 1));
        ticket.setValid(true);
        when(ticketRepository.save(Mockito.<Ticket>any())).thenReturn(ticket);

        TicketRequest request = new TicketRequest();
        request.setAttendeesCount("3");
        request.setEventId(1L);
        Ticket actualReserveTicketResult = ticketServiceImpl.reserveTicket(request);
        verify(ticketRepository).save(Mockito.<Ticket>any());
        assertSame(ticket, actualReserveTicketResult);
    }
}

