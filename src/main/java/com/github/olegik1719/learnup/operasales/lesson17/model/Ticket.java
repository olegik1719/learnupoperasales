package com.github.olegik1719.learnup.operasales.lesson17.model;

import lombok.*;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Ticket {
    private Event event;
    @EqualsAndHashCode.Include
    private int id;
}
