package com.github.olegik1719.learnup.operasales.lesson17.model;

public class Ticket {
    private Event event;
    private double price;

    public Ticket() {
    }

    public Ticket(Event event, double price) {
        this.event = event;
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
