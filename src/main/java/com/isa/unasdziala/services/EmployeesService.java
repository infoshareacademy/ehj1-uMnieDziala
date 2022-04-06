package com.isa.unasdziala.services;

import com.isa.unasdziala.domain.Employee;
import com.isa.unasdziala.repository.EmployeesRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record EmployeesService(EmployeesRepository employeeRepository) {

    public List<LocalDate> getAllBusyDayForEmployee(Employee employee) {
        List<LocalDate> busyDays = new ArrayList<>();
        return busyDays;
    }

}