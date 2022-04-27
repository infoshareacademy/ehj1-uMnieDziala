package com.isa.unasdziala.service;

import com.isa.unasdziala.dto.HolidayDto;
import com.isa.unasdziala.request.AddHolidaysRequest;
import com.isa.unasdziala.request.DeleteHolidaysRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayService {
    public List<HolidayDto> findAll(Long userId) {
        throw new RuntimeException("Not implemented");
    }

    public HolidayDto findHolidayById(Long userId, Long holidayId) {
        throw new RuntimeException("Not implemented");
    }

    public List<HolidayDto> addHoliday(Long userId, AddHolidaysRequest addHolidaysRequest) {
        throw new RuntimeException("Not implemented");
    }

    public void deleteById(Long userId, DeleteHolidaysRequest deleteHolidaysRequest) {
        throw new RuntimeException("Not implemented");
    }
}
