package com.github.olegik1719.learnup.operasales.lesson17.services;

import com.github.olegik1719.learnup.operasales.lesson17.annotations.Notifiable;
import com.github.olegik1719.learnup.operasales.lesson17.model.Opera;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.H2EventRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.H2OperaRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.H2TicketRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.EventEntity;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.OperaEntity;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DbSalesService {
    H2OperaRepo operaRepo;
    H2EventRepo eventRepo;
    H2TicketRepo ticketRepo;

    @Autowired
    public DbSalesService(H2OperaRepo operaRepo, H2EventRepo eventRepo, H2TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
        this.operaRepo = operaRepo;
        this.eventRepo = eventRepo;
    }


    public boolean addOpera(String name, String author, String description, Opera.Category category, int capacity) {
        OperaEntity operaEntity = operaRepo.findEntityByNameAuthor(name, author);
        if (operaEntity != null) {
            return false;
        } else {
            operaEntity = new OperaEntity().setAuthor(author).setName(name).setDescription(description).setCategory(category.ordinal()).setFullCapacity(capacity);
            OperaEntity saved = operaRepo.saveAndFlush(operaEntity);
            return saved.getId() != null;
        }
    }

    @Notifiable
    @Transactional
    public Boolean modifyOpera(String oldName, String oldAuthor, String newName, String newAuthor, String newDescription, Opera.Category newCategory, int capacity) {
        OperaEntity oldOpera = operaRepo.findEntityByNameAuthor(oldName, oldAuthor);
        if (oldOpera == null) {
            return false;
        }
        try {
            OperaEntity newOpera = operaRepo.getForUpdate(oldOpera.getId())
                    .setAuthor(newAuthor == null ? oldOpera.getAuthor() : newAuthor)
                    .setName(newName == null ? oldOpera.getName() : newName)
                    .setDescription(newDescription == null ? oldOpera.getDescription() : newDescription)
                    .setCategory(newCategory == null ? oldOpera.getCategory() : newCategory.ordinal())
                    .setFullCapacity(capacity <= 0 ? oldOpera.getFullCapacity() : capacity);

            return operaRepo.saveAndFlush(newOpera).getId() != null;
        } catch (DataAccessException err) {
            throw new RuntimeException(err);
        }
    }

    public boolean removeOpera(String name, String author) {
        OperaEntity opera = operaRepo.findEntityByNameAuthor(name, author);
        operaRepo.delete(opera);
        operaRepo.flush();
        return true;
    }

    @Notifiable
    public Boolean addEvent(OperaEntity opera, Date date) {
        Optional<EventEntity> eventSearch = eventRepo.findEntityByNameAuthorDate(opera.getName(), opera.getAuthor(), date);
        if (eventSearch.isPresent()) {
            return false;
        }
        EventEntity event = new EventEntity().setEventDate(date).setIdOpera(opera);
        return eventRepo.saveAndFlush(event).getId() != null;
    }


    @Notifiable
    public Boolean removeEvent(OperaEntity opera, Date date) {
        Optional<EventEntity> eventSearch = eventRepo.findEntityByNameAuthorDate(opera.getName(), opera.getAuthor(), date);
        if (eventSearch.isEmpty()) {
            return false;
        }
        eventRepo.delete(eventSearch.get());
        return true;
    }

    @Transactional
    public Long buyTicket(OperaEntity opera, Date date) {
        Optional<EventEntity> event = eventRepo.findEntityByNameAuthorDate(opera.getName(), opera.getAuthor(), date);
        if (event.isEmpty()) {
            throw new IllegalArgumentException("Такого мероприятия нет");
        }
        try {
            EventEntity eventEntity = eventRepo.getForUpdate(event.get().getId());
            if (eventEntity.getIdOpera().getFullCapacity() > eventEntity.getCountSold()) {
                eventEntity.setCountSold(eventEntity.getCountSold() + 1);
                TicketEntity ticket = new TicketEntity().setEvent(eventEntity);
                TicketEntity savedTicket = ticketRepo.saveAndFlush(ticket);
                eventRepo.saveAndFlush(eventEntity);
                return savedTicket.getId();
            }

            throw new IllegalArgumentException("Билетов на мероприятие больше нет!");
        } catch (DataAccessException err) {
            throw new RuntimeException(err);
        }

    }


    public boolean refundTicket(long id) {
        Optional<TicketEntity> optionalTicket = ticketRepo.findById(id);
        if (optionalTicket.isEmpty()) {
            throw new IllegalArgumentException("Такого билета не существует");
        }
        EventEntity event = optionalTicket.get().getEvent();
        event.setCountSold(event.getCountSold() - 1);
        ticketRepo.delete(optionalTicket.get());
        eventRepo.saveAndFlush(event);
        return true;
    }

    public List<OperaEntity> getAllOpera(){
        return operaRepo.findAll().stream().collect(Collectors.toList());
    }

    public OperaEntity getOpera(String name, String author){
        return operaRepo.findEntityByNameAuthor(name, author);
    }
}
