package com.github.olegik1719.learnup.operasales.lesson17.repository.simple;

import com.github.olegik1719.learnup.operasales.lesson17.model.Event;
import com.github.olegik1719.learnup.operasales.lesson17.repository.EventRepo;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SimpleEventRepo implements EventRepo {

    final private Set<Event> events = new HashSet<>();

    @Override
    public Collection<Event> findByDate(Date dateEvent) {
        return events.stream().filter(r -> r.getDate().equals(dateEvent)).collect(Collectors.toSet());
    }

    @Override
    public Collection<Event> getAll() {
        return events;
    }

    @Override
    public boolean add(Event event) {
        return events.add(event);
    }

    @Override
    public boolean remove(Event event) {
        return events.remove(event);
    }


}
