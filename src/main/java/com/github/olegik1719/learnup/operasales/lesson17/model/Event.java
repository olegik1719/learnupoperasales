package com.github.olegik1719.learnup.operasales.lesson17.model;

import java.util.Date;

public class Event {
    private Date dateEvent;
    private Opera operaEvent;

    public Event(Date dateEvent, Opera operaEvent) {
        this.dateEvent = dateEvent;
        this.operaEvent = operaEvent;
    }

    public Event() {
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Opera getOperaEvent() {
        return operaEvent;
    }

    public void setOperaEvent(Opera operaEvent) {
        this.operaEvent = operaEvent;
    }
}
