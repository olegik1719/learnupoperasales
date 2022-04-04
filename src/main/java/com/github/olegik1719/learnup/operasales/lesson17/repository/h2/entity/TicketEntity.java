package com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ticket")
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "idEvent")
    @ManyToOne(cascade = CascadeType.MERGE)
    private EventEntity event;
}
