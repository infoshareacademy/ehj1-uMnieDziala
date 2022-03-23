package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;

import java.util.List;

public class NonWorkingDaysRepository {
    private final List<Day> nonWorkingDays = importNonWorkingDays();

    private List<Day> importNonWorkingDays() {
        return List.of();
    }
}
