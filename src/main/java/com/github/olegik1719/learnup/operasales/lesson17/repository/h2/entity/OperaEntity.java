package com.github.olegik1719.learnup.operasales.lesson17.repository.h2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;

@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "opera")
@Table(name = "opera")
public class OperaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "author", length = 100, nullable = false)
    private String author;
    @Column(name = "description", length = 4000)
    private String description;
    @Column(name = "category")
    private int category;
    @Column(name = "capacity")
    private int fullCapacity;


    @OneToMany(mappedBy = "idOpera", fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private Collection<EventEntity> events;
}
