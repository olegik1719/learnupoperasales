package com.github.olegik1719.learnup.operasales.lesson17.repository.h2;

import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.EventEntity;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.OperaEntity;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface H2TicketRepo extends JpaRepository<TicketEntity, Long> {
    @Query("from ticket where idEvent = :event")
    Collection<TicketEntity> getTicketsByIdEvent(@Param(value = "event") long idEvent);
    default Collection<TicketEntity> getTicketsByEvent(@Param(value = "event") EventEntity event){
        Collection<TicketEntity> ticks = getTicketsByIdEvent(event.getId());
        return ticks;
    }
}
