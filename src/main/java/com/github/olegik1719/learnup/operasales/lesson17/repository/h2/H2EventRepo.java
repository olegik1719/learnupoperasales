package com.github.olegik1719.learnup.operasales.lesson17.repository.h2;

import com.github.olegik1719.learnup.operasales.lesson17.model.Event;
import com.github.olegik1719.learnup.operasales.lesson17.repository.EventRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.EventEntity;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.OperaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

public interface H2EventRepo extends JpaRepository<EventEntity, Long> {

    @Query("from event e join opera o on o.id = e.idOpera where o.name = :name and o.author = :author and e.eventDate = :date")
    Optional<EventEntity> findEntityByNameAuthorDate(@Param(value = "name")String name, @Param(value = "author")String author, @Param(value = "date")Date date);

    @Query("from event where eventDate = :date")
    Collection<EventEntity> findEntityByDate(@Param(value = "date") Date dateEvent);

}
