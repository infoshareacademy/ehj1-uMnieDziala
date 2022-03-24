package com.isa.unasdziala.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Day {
    private Long id;
    private LocalDate date;
    private String name;
    private String description;

    public Day(LocalDate date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }
}
