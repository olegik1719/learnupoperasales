package com.github.olegik1719.learnup.operasales.lesson17.repository;

import com.github.olegik1719.learnup.operasales.lesson17.model.Event;
import com.github.olegik1719.learnup.operasales.lesson17.model.Ticket;

import java.util.List;

public interface TicketRepo {
    Ticket buyTicket(Event event);
    boolean refundTicket(Ticket ticket);
    List<Ticket> getTicketsByEvent(Event event);
}
