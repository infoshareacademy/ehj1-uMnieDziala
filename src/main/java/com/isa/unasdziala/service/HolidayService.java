package com.isa.unasdziala.service;

import com.isa.unasdziala.dto.HolidayDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
public class HolidayService {
    public List<HolidayDto> findAll(Long userId) {
        throw new RuntimeException("Not implemented");
    }

    public HolidayDto addHoliday(Long userId, Collection<LocalDate> dates) {
        throw new RuntimeException("Not implemented");
    }
}
