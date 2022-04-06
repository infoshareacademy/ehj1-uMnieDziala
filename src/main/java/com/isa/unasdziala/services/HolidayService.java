package com.isa.unasdziala.services;

import com.isa.unasdziala.domain.Holiday;
import com.isa.unasdziala.repository.HolidayRepository;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HolidayService {

    private final HolidayRepository holidayRepository;
    Logger log = LoggerFactory.getLogger(NonWorkingDaysReader.class);

    public HolidayService(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    public void addDay(Holiday holiday) {
        log.info("Add new holiday day {} to repository", holiday.getDate().toString());
        holidayRepository.save(holiday);
    }
}
