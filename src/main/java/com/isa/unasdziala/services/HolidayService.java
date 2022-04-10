package com.isa.unasdziala.services;

import com.isa.unasdziala.domain.entity.Employee;
import com.isa.unasdziala.domain.entity.Holiday;
import com.isa.unasdziala.repository.HolidayRepository;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class HolidayService {

    private final HolidayRepository holidayRepository;
    Logger log = LoggerFactory.getLogger(NonWorkingDaysReader.class);

    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public List<Holiday> findAll() {
        return holidayRepository.findAll();
    }

    public Holiday addHoliday(LocalDate date, Employee employee) throws IllegalArgumentException {
        return holidayRepository.add(date, employee)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Holiday with date already exists: " + date.toString()
                        )
                );
    }
}