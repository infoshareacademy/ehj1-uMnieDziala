package com.isa.unasdziala.service;

import com.isa.unasdziala.dto.HolidayDto;
import com.isa.unasdziala.exception.ResourceNotFoundException;
import com.isa.unasdziala.repository.HolidayRepository;
import com.isa.unasdziala.request.AddHolidaysRequest;
import com.isa.unasdziala.request.DeleteHolidaysRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    public HolidayService(HolidayRepository holidayRepository, EmployeeService employeeService, ModelMapper modelMapper) {
        this.holidayRepository = holidayRepository;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    public List<HolidayDto> findAll(Long userId) {
        return holidayRepository.findAllByEmployees_Id(userId)
                .stream()
                .map(holiday -> modelMapper.map(holiday, HolidayDto.class))
                .toList();
    }

    public HolidayDto findHolidayById(Long userId, Long holidayId) {
        return holidayRepository.findByIdAndEmployees_Id(holidayId, userId)
                .map(holiday -> modelMapper.map(holiday, HolidayDto.class))
                .orElseThrow(() -> new ResourceNotFoundException(format("Holiday with id %d not found.", holidayId)));
    }

    public List<HolidayDto> addHoliday(Long userId, AddHolidaysRequest addHolidaysRequest) {
        throw new RuntimeException("Not implemented");
    }

    public void deleteById(Long userId, DeleteHolidaysRequest deleteHolidaysRequest) {
        throw new RuntimeException("Not implemented");
    }
}
