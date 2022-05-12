package com.isa.unasdziala.service;

import com.isa.unasdziala.configuration.HolidayConfiguration;
import com.isa.unasdziala.dto.HolidayDto;
import com.isa.unasdziala.exception.OutOfFreeDaysException;
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
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

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

    public List<HolidayDto> findAll(Long employeeId) {
        return holidayRepository.findAllByEmployeesId(employeeId)
                .stream()
                .map(holiday -> modelMapper.map(holiday, HolidayDto.class))
                .toList();
    }

    public HolidayDto findHolidayById(Long employeeId, Long holidayId) {
        return modelMapper.map(findHolidayEntityById(employeeId, holidayId), HolidayDto.class);
    }

    private Holiday findHolidayEntityById(Long employeeId, Long holidayId) {
        return holidayRepository.findByIdAndEmployeesId(holidayId, employeeId)
                .orElseThrow(() -> new ResourceNotFoundException(format("Holiday with id %d not found or does not belong to this employee", holidayId)));
    }

    public List<HolidayDto> addHoliday(Long userId, AddHolidaysRequest addHolidaysRequest) {

        Employee employee = employeeRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));


        Set<LocalDate> bussyDaysFromEmployeeHolidaysDays = holidayRepository.findAllByEmployeesId(userId).stream()
                .map(Holiday::getDate)
                .collect(Collectors.toSet());
        Set<LocalDate> bussyDaysFromHolidayRepo = holidayRepository.findAll().stream()
                .filter(holiday -> holiday.getEmployees().stream().toList()
                        .size() >= holidayConfiguration.getMaxAbsence())
                .map(Holiday::getDate)
                .collect(Collectors.toSet());
        Set<LocalDate> bussyDays = new HashSet<>();
        bussyDays.addAll(bussyDaysFromEmployeeHolidaysDays);
        bussyDays.addAll(bussyDaysFromHolidayRepo);

        Set<LocalDate> requestDates = addHolidaysRequest.getDates().stream()
                .filter(date -> !bussyDays.contains(date))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .collect(Collectors.toSet());

        Set<Holiday> holidaysToAdd = requestDates.stream()
                .map(Holiday::new)
                .peek(holiday -> holiday.getEmployees().add(employee))
                .collect(Collectors.toSet());


        if (employee.getHolidays() < holidaysToAdd.size()) {
            throw new OutOfFreeDaysException();
        }
        employee.setHolidays(employee.getHolidays() - holidaysToAdd.size());
        employee.getHolidayDays().addAll(holidaysToAdd);
        employeeRepository.save(employee);

        return holidaysToAdd.stream()
                .map(holiday -> modelMapper.map(holiday, HolidayDto.class)).toList();
    }

    public void deleteById(Long employeeId, DeleteHolidaysRequest deleteHolidaysRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + employeeId + " not found"));

        Set<Holiday> holidaysToDelete = deleteHolidaysRequest.getHolidaysId().stream()
                .map(id -> findHolidayEntityById(employeeId, id))
                .collect(Collectors.toSet());

        employee.setHolidays(employee.getHolidays() + holidaysToDelete.size());
        Set<Holiday> holidayDays = employee.getHolidayDays();
        holidayDays.removeAll(holidaysToDelete);
        employee.setHolidayDays(holidayDays);
        employeeRepository.save(employee);
        holidayRepository.deleteAll(holidaysToDelete);
    }
}
