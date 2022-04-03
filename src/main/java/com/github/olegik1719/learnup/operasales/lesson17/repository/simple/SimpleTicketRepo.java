package com.github.olegik1719.learnup.operasales.lesson17.repository.simple;

import com.github.olegik1719.learnup.operasales.lesson17.model.Event;
import com.github.olegik1719.learnup.operasales.lesson17.model.Ticket;
import com.github.olegik1719.learnup.operasales.lesson17.repository.TicketRepo;

import java.util.*;

public class SimpleTicketRepo implements TicketRepo {

    private int ticketNumber = 0;
    private Set<Ticket> tickets = new HashSet<>();

    @Override
    public Ticket buyTicket(Event event) {
        if (event.getCountSold() < event.getOpera().getFullCapacity()) {
            Ticket ticket = new Ticket().setEvent(event).setId(ticketNumber++);
            tickets.add(ticket);
            event.setCountSold(event.getCountSold() + 1);
            return ticket;
        }
        throw new IllegalArgumentException("Билетов на мероприятие больше нет");
    }

    @Override
    public boolean refundTicket(Ticket ticket) {
        if (ticket == null) {
            return false;
        }
        Optional<Ticket> findTicket = tickets.stream().filter(t -> t.equals(ticket)).findAny();
        if (findTicket.isEmpty()) {
            return false;
        }
        Ticket foundTicket = findTicket.get();
        Event event = foundTicket.getEvent();
        event.setCountSold(event.getCountSold() - 1);
        return tickets.remove(ticket);
    }

    @Override
    public List<Ticket> getTicketsByEvent(Event event) {
        return new ArrayList<>(tickets);
    }
}
