package com.isa.unasdziala.services;

import com.isa.unasdziala.domain.Employee;
import com.isa.unasdziala.domain.Holiday;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesService {

    public List<LocalDate> getAllBusyDayForEmployee(Employee employee) {
        List<LocalDate> busyDays = new ArrayList<>();


        busyDays.addAll(employee.getHolidayDays().stream()
                .map(Holiday::getDate)
                .collect(Collectors.toList()));

        return busyDays;
    }

}