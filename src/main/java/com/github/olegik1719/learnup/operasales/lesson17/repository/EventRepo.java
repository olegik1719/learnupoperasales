package com.github.olegik1719.learnup.operasales.lesson17.repository;

import com.github.olegik1719.learnup.operasales.lesson17.model.Event;
import com.github.olegik1719.learnup.operasales.lesson17.model.Opera;

import java.util.Collection;
import java.util.Date;

public interface EventRepo {

    Collection<Event> findByDate(Date dateEvent);

    Collection<Event> getAll();

    boolean add(Event event);

    boolean remove(Event event);

    default Event getEvent(Opera opera, Date date) {
        return findByDate(date).stream().filter(op -> op.equals(new Event().setOpera(opera).setDate(date))).findAny().orElse(null);
    }

    default Event getEvent(String nameOpera, String authorOpera, Date date) {
        return getEvent(new Opera().setName(nameOpera).setAuthor(authorOpera), date);
    }

}
