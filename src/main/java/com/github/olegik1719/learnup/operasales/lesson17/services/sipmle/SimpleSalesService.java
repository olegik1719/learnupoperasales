package com.github.olegik1719.learnup.operasales.lesson17.services.sipmle;

import com.github.olegik1719.learnup.operasales.lesson17.annotations.Notifiable;
import com.github.olegik1719.learnup.operasales.lesson17.model.Event;
import com.github.olegik1719.learnup.operasales.lesson17.model.Opera;
import com.github.olegik1719.learnup.operasales.lesson17.model.Ticket;
import com.github.olegik1719.learnup.operasales.lesson17.repository.EventRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.OperaRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.TicketRepo;
import com.github.olegik1719.learnup.operasales.lesson17.services.SalesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class SimpleSalesService implements SalesService {
    private OperaRepo operaRepo;
    private EventRepo eventRepo;
    private TicketRepo ticketRepo;

    @Autowired
    public SimpleSalesService(OperaRepo operaRepo, EventRepo eventRepo, TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
        this.operaRepo = operaRepo;
        this.eventRepo = eventRepo;
    }

    @Override
    public boolean addOpera(String name, String author, String description, Opera.Category category, int capacity) {
        Opera opera = new Opera().setAuthor(author).setName(name).setDescription(description).setCategory(category).setFullCapacity(capacity);
        return operaRepo.add(opera);
    }

    @Override
    @Notifiable
    public Boolean modifyOpera(String oldName, String oldAuthor, String newName, String newAuthor, String newDescription, Opera.Category newCategory, int capacity) {
        Opera oldOpera = operaRepo.findByNameAuthor(oldName, oldAuthor);
        if (oldOpera == null) {
            return false;
        }
        Opera newOpera = new Opera()
                .setAuthor(newAuthor == null ? oldOpera.getAuthor() : newAuthor)
                .setName(newName == null ? oldOpera.getName() : newName)
                .setDescription(newDescription == null ? oldOpera.getDescription() : newDescription)
                .setCategory(newCategory == null ? oldOpera.getCategory() : newCategory)
                .setFullCapacity(capacity <= 0 ? oldOpera.getFullCapacity() : capacity);

        return operaRepo.update(oldOpera, newOpera);
    }

    @Override
    public boolean removeOpera(String name, String author) {
        Opera opera = new Opera().setAuthor(author).setName(name);
        return operaRepo.remove(opera);
    }

    @Override
    @Notifiable
    public Boolean addEvent(Opera opera, Date date) {
        Event event = new Event().setDate(date).setOpera(opera);
        return eventRepo.add(event);
    }

    @Override
    @Notifiable
    public Boolean removeEvent(Opera opera, Date date) {
        Event event = new Event().setDate(date).setOpera(opera);
        return eventRepo.remove(event);
    }

    @Override
    public Integer buyTicket(Opera opera, Date date) {
        Event event = eventRepo.getEvent(opera, date);
        return ticketRepo.buyTicket(event).getId();
    }

    @Override
    public boolean refundTicket(int id) {
        Ticket ticket = new Ticket().setId(id);
        return ticketRepo.refundTicket(ticket);
    }
}
