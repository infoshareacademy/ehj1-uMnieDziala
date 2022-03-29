package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;

import java.util.List;

public class NonWorkingDaysRepository {
    private final List<Day> nonWorkingDays = importNonWorkingDays();

    private List<Day> importNonWorkingDays() {
        NonWorkingDaysReader nonWorkinDays = new NonWorkingDaysReader();
        return nonWorkingDays.g
    }
}
