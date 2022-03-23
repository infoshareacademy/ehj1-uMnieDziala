package com.isa.unasdziala.services;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.repository.NonWorkingDaysRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class NonWorkingDayService {

    private final NonWorkingDaysRepository nonWorkingDaysRepository = new NonWorkingDaysRepository();

    public Day add(Day day) {
        nonWorkingDaysRepository.add(day);
        return day;
    }

    public List<Day> findAll() {
        return nonWorkingDaysRepository.findAll();
    }

    public Day findByDate(LocalDate date) {
        Optional<Day> nonWorkingDay = nonWorkingDaysRepository.findNonWorkingDayByLocalDate(date);
        return nonWorkingDay.orElseThrow(() -> new IllegalArgumentException("Wrong date"));
    }

    public Day deleteByDate(LocalDate date) {
        Optional<Day> deletedDay = nonWorkingDaysRepository.deleteByDate(date);
        return deletedDay.orElseThrow(() -> new IllegalArgumentException("Wrong date"));
    }

    public Day updateByDate(LocalDate date, Day newDay) {
        Optional<Day> updatedDay = nonWorkingDaysRepository.updateByDate(date, newDay);
        return updatedDay.orElseThrow(() -> new IllegalArgumentException("Wrong date"));
    }


}
