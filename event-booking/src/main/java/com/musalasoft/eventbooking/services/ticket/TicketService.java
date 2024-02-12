package com.musalasoft.eventbooking.services.ticket;


import com.musalasoft.eventbooking.dtos.requests.TicketRequest;
import com.musalasoft.eventbooking.models.Ticket;

public interface TicketService {

    Ticket reserveTicket(TicketRequest request);
}
