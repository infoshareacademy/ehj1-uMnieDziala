package com.isa.unasdziala.domain;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Getter
public class Holiday {

    public static final String TABLE_NAME = "holidays";

    private UUID id;
    private LocalDate date;
    private Set<Employee> owners;

}
