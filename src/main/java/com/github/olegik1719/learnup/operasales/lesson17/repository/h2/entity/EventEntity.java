package com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "event")
@Table(name = "event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "eventDate")
    private Date eventDate;


    @Column(name = "soldTickets")
    private int countSold;

    @JoinColumn(name = "idOpera")
    @ManyToOne(cascade = CascadeType.MERGE)
    private OperaEntity idOpera;


    @OneToMany(mappedBy = "event")
    @ToString.Exclude
    @JsonIgnore
    private Collection<TicketEntity> tickets;

}
