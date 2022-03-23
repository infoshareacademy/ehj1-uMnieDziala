package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class NonWorkingDaysRepository {
    private final List<Day> nonWorkingDays = importNonWorkingDays();

    public Day add(Day day) {
        nonWorkingDays.add(day);
        return day;
    }

    public Optional<Day> findNonWorkingDayByLocalDate(LocalDate date) {
        return nonWorkingDays.stream().filter(day -> day.getDate().equals(date)).findFirst();
    }

    public List<Day> findAll() {
        return List.copyOf(nonWorkingDays);
    }

    public Optional<Day> deleteByDate(LocalDate date) {
        Optional<Day> day = findNonWorkingDayByLocalDate(date);
        day.ifPresent(nonWorkingDays::remove);
        return day;
    }

    public Optional<Day> updateByDate(LocalDate date, Day newDay) {
        Optional<Day> day = findNonWorkingDayByLocalDate(date);
        if (day.isPresent()) {
            Day dayEntity = day.get();
            dayEntity.setDate(newDay.getDate());
            dayEntity.setName(newDay.getName());
            dayEntity.setDescription(newDay.getDescription());
        }
        return day;
    }


    private List<Day> importNonWorkingDays() {
        throw new RuntimeException("No Implemented");
    }

}
