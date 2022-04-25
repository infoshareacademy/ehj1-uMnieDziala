package com.isa.unasdziala.services;

import com.isa.unasdziala.adapters.EmployeeAdapter;
import com.isa.unasdziala.domain.entity.Employee;
import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.repository.EmployeesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeesRepository employeesRepository;
    private final EmployeeAdapter employeeAdapter;

    private final List<Employee> employees = new ArrayList<>();


    public EmployeeService(EmployeesRepository employeesRepository, EmployeeAdapter employeeAdapter) {
        this.employeesRepository = employeesRepository;
        this.employeeAdapter = employeeAdapter;
    }



    public List<EmployeeDto> findAll() {
        return employees.stream().map(employeeAdapter::convertToEmployeeDto).collect(Collectors.toList());
    }

//    public EmployeeDto findByFirstNameAndLastName(String firstName, String lastName) throws IllegalArgumentException {
//        return employeesRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(
//                () -> new IllegalArgumentException("Wrong first name " + firstName + " and last name " + lastName)
//        );
//    }

    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws IllegalArgumentException {
        return employeesRepository.add(employeeDto)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Employee with first name and last name already exists: " + employeeDto.getFirstName() + " " + employeeDto.getLastName()
                        )
                );
    }

    public EmployeeDto deleteEmployee(String firstName, String lastName) throws IllegalArgumentException {
        return employeesRepository.delete(firstName, lastName)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Employee with first name and last name does not exist: " + firstName + " " + lastName
                        )
                );
    }

    public EmployeeDto updateEmployee(String oldFirstName, String oldLastName, EmployeeDto newEmployeeDto) throws IllegalArgumentException {
        return employeesRepository.update(oldFirstName, oldLastName, newEmployeeDto)
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "Employee with first name and last name does not exist or already exists with new name and lastname: " + oldFirstName + " " + oldLastName
                        )
                );
    }

    public void importFile() {
        employeesRepository.importEmployees();
    }

}
