package com.musalasoft.eventbooking.services.ticket;

import com.musalasoft.eventbooking.dtos.requests.TicketRequest;
import com.musalasoft.eventbooking.models.Ticket;
import com.musalasoft.eventbooking.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Override
    public Ticket reserveTicket(TicketRequest request) {
        Ticket ticket = new Ticket();
        ticket.setValid(true);
        ticket.setEventId(String.valueOf(request.getEventId()));
        ticket.setTimeCreated(LocalDate.now());

        var savedTicket = ticketRepository.save(ticket);
        return savedTicket;
    }

}
