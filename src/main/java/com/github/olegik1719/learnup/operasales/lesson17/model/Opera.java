package com.github.olegik1719.learnup.operasales.lesson17.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, doNotUseGetters = true)
@Getter
@Setter
@ToString
public class Opera {

    @EqualsAndHashCode.Include
    private String name;
    @EqualsAndHashCode.Include
    private String author;
    private String description;
    private Category category;
    private int fullCapacity;

    public enum Category {
        NO_LIMITS,
        OVER_03,
        OVER_06,
        OVER_16,
        OVER_18
    }
}
