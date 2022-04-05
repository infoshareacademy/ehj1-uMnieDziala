package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class NonWorkingDaysRepository {
    private final List<Day> nonWorkingDays = importNonWorkingDays();

    public Day add(Day day) {
        day.setId(UUID.randomUUID());
        nonWorkingDays.add(day);
        return day;
    }

    public Optional<Day> findById(UUID id) {
        return nonWorkingDays.stream().filter(day -> day.getId().equals(id)).findFirst();
    }

    public List<Day> findDaysByLocalDate(LocalDate date) {
        return nonWorkingDays.stream().filter(day -> day.getDate().equals(date)).collect(Collectors.toList());
    }

    public List<Day> findAll() {
        return List.copyOf(nonWorkingDays);
    }

    public Optional<Day> deleteById(UUID id) {
        Optional<Day> day = findById(id);
        day.ifPresent(nonWorkingDays::remove);
        return day;
    }

    public Optional<Day> updateById(UUID id, Day newDay) {
        Optional<Day> optionalDay = findById(id);
        if (optionalDay.isPresent()) {
            Day day = optionalDay.get();
            day.setDate(newDay.getDate());
            day.setName(newDay.getName());
            day.setDescription(newDay.getDescription());
        }
        return optionalDay;
    }

    private List<Day> importNonWorkingDays() {
        return new ArrayList<>();
    }

}
