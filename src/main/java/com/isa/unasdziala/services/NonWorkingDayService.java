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

    public List<Day> findDaysByDate(LocalDate date) {
        return nonWorkingDaysRepository.findDaysByLocalDate(date);
    }

    public Day findById(Long id) throws IllegalArgumentException {
        return nonWorkingDaysRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Wrong ID"));
    }

    public Day deleteById(Long id) throws IllegalArgumentException {
        Optional<Day> deletedDay = nonWorkingDaysRepository.deleteById(id);
        return deletedDay.orElseThrow(() -> new IllegalArgumentException("Wrong ID"));
    }

    public Day updateById(Long id, Day newDay) throws IllegalArgumentException {
        Optional<Day> updatedDay = nonWorkingDaysRepository.updateById(id, newDay);
        return updatedDay.orElseThrow(() -> new IllegalArgumentException("Wrong ID"));
    }


}
