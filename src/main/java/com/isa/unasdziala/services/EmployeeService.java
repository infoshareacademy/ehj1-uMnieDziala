package com.isa.unasdziala.services;

import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.repository.EmployeesRepository;

import java.util.List;

public class EmployeeService {
    private EmployeesRepository employeesRepository;

    public EmployeeService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<EmployeeDto> findAll() {
        return employeesRepository.findAll();
    }

    public EmployeeDto findByFirstNameAndLastName(String firstName, String lastName) {
        return employeesRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(
                () -> new IllegalArgumentException("Wrong first name " + firstName + " and last name " + lastName)
        );
    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws IllegalArgumentException {
        return employeesRepository.add(employeeDto)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Employee with this first name and last name already exists: " + employeeDto.getFirstName() + " " + employeeDto.getLastName()
                        )
                );
    }




}
