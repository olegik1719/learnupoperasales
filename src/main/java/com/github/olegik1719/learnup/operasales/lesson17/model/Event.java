package com.github.olegik1719.learnup.operasales.lesson17.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
@ToString
public class Event {
    @EqualsAndHashCode.Include
    private Date date;
    @EqualsAndHashCode.Include
    private Opera opera;
    private int countSold;

}
