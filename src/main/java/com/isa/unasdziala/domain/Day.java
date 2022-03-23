package com.isa.unasdziala.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Day {
    private LocalDate date;
    private String name;
    private String description;
}
