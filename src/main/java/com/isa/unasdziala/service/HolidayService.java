package com.isa.unasdziala.service;

import com.isa.unasdziala.configuration.HolidayConfiguration;
import com.isa.unasdziala.dto.HolidayDto;
import com.isa.unasdziala.exception.ResourceNotFoundException;
import com.isa.unasdziala.model.Employee;
import com.isa.unasdziala.model.Holiday;
import com.isa.unasdziala.repository.EmployeeRepository;
import com.isa.unasdziala.repository.HolidayRepository;
import com.isa.unasdziala.request.AddHolidaysRequest;
import com.isa.unasdziala.request.DeleteHolidaysRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.aspectj.runtime.internal.Conversions.longValue;

@Service
public class HolidayService {
    private final HolidayRepository holidayRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final HolidayConfiguration holidayConfiguration;

    public HolidayService(HolidayRepository holidayRepository, EmployeeService employeeService, ModelMapper modelMapper, EmployeeRepository employeeRepository, HolidayConfiguration holidayConfiguration) {
        this.holidayRepository = holidayRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.holidayConfiguration = holidayConfiguration;
    }

    public List<HolidayDto> findAll(Long userId) {
        return holidayRepository.findAllByEmployeesId(userId)
                .stream()
                .map(holiday -> modelMapper.map(holiday, HolidayDto.class))
                .toList();
    }

    public HolidayDto findHolidayById(Long userId, Long holidayId) {
        return holidayRepository.findByIdAndEmployeesId(holidayId, userId)
                .map(holiday -> modelMapper.map(holiday, HolidayDto.class))
                .orElseThrow(() -> new ResourceNotFoundException(format("Holiday with id %d not found.", holidayId)));
    }

    public List<HolidayDto> addHoliday(Long userId, AddHolidaysRequest addHolidaysRequest) {

        Optional<Employee> employee = employeeRepository.findById(userId);

        Set<LocalDate> bussyDaysFromEmployeeHolidaysDays = holidayRepository.findAllByEmployeesId(userId).stream()
                .map(Holiday::getDate)
                .collect(Collectors.toSet());
        Set<LocalDate> bussyDaysFromHolidayRepo = holidayRepository.findAll().stream()
                .filter(holiday -> holiday.getEmployees().stream()
                        .collect(Collectors.toList())
                        .size() >= holidayConfiguration.getMaxAbsence())
                .map(Holiday::getDate)
                .collect(Collectors.toSet());
        Set<LocalDate> bussyDays = new HashSet<>();
        bussyDays.addAll(bussyDaysFromEmployeeHolidaysDays);
        bussyDays.addAll(bussyDaysFromHolidayRepo);

        addHolidaysRequest.getDates().stream()
                .filter(date -> !bussyDays.contains(date))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY || date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .limit(longValue(employee.get().getHolidays()))
                .collect(Collectors.toSet());

        Set<Holiday> holidaysToAdd = addHolidaysRequest.getDates().stream()
                .map(date -> new Holiday(date))
                .collect(Collectors.toSet());

        employee.get().setHolidays(employee.get().getHolidays() - addHolidaysRequest.getDates().size());
        employee.get().getHolidayDays().addAll(holidaysToAdd);
        employeeRepository.save(employee.get());

        return holidaysToAdd.stream()
                .map(holiday -> modelMapper.map(holiday, HolidayDto.class))                .toList();
    }

    public void deleteById(Long userId, DeleteHolidaysRequest deleteHolidaysRequest) {
        Optional<Employee> employee = employeeRepository.findById(userId);

        Set<Holiday> holidaysToDelete = deleteHolidaysRequest.getHolidaysId().stream()
                .map(id -> findHolidayById(userId, id))
                .map(holiday -> modelMapper.map(holiday, Holiday.class))
                .collect(Collectors.toSet());

        employee.get().setHolidays(employee.get().getHolidays() + deleteHolidaysRequest.getHolidaysId().size());
        employee.get().getHolidayDays().removeAll(holidaysToDelete);
        employeeRepository.save(employee.get());

    }
}
