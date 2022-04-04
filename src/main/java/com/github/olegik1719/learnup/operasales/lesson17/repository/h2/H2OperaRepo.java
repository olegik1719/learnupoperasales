package com.github.olegik1719.learnup.operasales.lesson17.repository.h2;

import com.github.olegik1719.learnup.operasales.lesson17.model.Opera;
import com.github.olegik1719.learnup.operasales.lesson17.repository.OperaRepo;
import com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity.OperaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.util.Collection;
import java.util.stream.Collectors;

public interface H2OperaRepo extends JpaRepository<OperaEntity, Long>{

    @Query("from opera where name = :name and author = :author")
    OperaEntity findEntityByNameAuthor(@Param(value = "name") String name, @Param(value = "author") String author);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("from opera b where b.id = :id")
    OperaEntity getForUpdate(Long id);
}
