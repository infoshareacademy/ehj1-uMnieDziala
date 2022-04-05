package com.isa.unasdziala.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Day {
    private UUID id;
    private LocalDate date;
    private String name;
    private String description;

    public Day(LocalDate date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }
}