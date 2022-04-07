package com.isa.unasdziala.services;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.repository.NonWorkingDaysRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class NonWorkingDayService {

    private final NonWorkingDaysRepository nonWorkingDaysRepository;

    public NonWorkingDayService(NonWorkingDaysRepository nonWorkingDaysRepository) {
        this.nonWorkingDaysRepository = nonWorkingDaysRepository;
    }

    public Day add(Day day) {
        nonWorkingDaysRepository.add(day);
        return day;
    }

    public List<Day> findAll() {
        return nonWorkingDaysRepository.findAll();
    }

    public Day findDayByDate(LocalDate date) throws IllegalArgumentException {
        return nonWorkingDaysRepository.findDayByLocalDate(date).orElseThrow(() -> new IllegalArgumentException("Wrong date"));
    }

    public Day deleteByDate(LocalDate date) throws IllegalArgumentException {
        Optional<Day> deletedDay = nonWorkingDaysRepository.deleteByDate(date);
        return deletedDay.orElseThrow(() -> new IllegalArgumentException("Wrong date " + date));
    }

    public Day updateByDate(Day newDay) throws IllegalArgumentException {
        Optional<Day> updatedDay = nonWorkingDaysRepository.updateByDate(newDay);
        return updatedDay.orElseThrow(() -> new IllegalArgumentException("Wrong date " + newDay.getDate()));
    }


}
