package com.isa.unasdziala.services;

import com.isa.unasdziala.domain.entities.Employee;
import com.isa.unasdziala.repository.EmployeesRepository;

import java.util.List;
import java.util.Optional;

public record EmployeesService(EmployeesRepository employeeRepository) {

    public Employee addEmployee(Employee employee) {
        employeeRepository.addEmployee(employee);
        return employee;
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAllEmployees();
    }

    public Employee findEmployeeByLastName(String lastName) throws IllegalArgumentException {
        Optional<Employee> employee = employeeRepository.findEmployeeByLastName(lastName);
        return employee.orElseThrow(() -> new IllegalArgumentException("Wrong last name: " + lastName));
    }

    public Employee updateEmployeeByLastName(String lastName, Employee newEmployee) throws IllegalArgumentException {
        Optional<Employee> deletedEmployee = employeeRepository.updateEmployeeByLastName(lastName, newEmployee);
        return deletedEmployee.orElseThrow(() -> new IllegalArgumentException("Wrong last name: " + lastName));
    }

    public Employee deleteEmployeeByLastName(String lastName) throws IllegalArgumentException {
        Optional<Employee> deletedEmployee = employeeRepository.deleteEmployeeByLastName(lastName);
        return deletedEmployee.orElseThrow(() -> new IllegalArgumentException("Wrong last name: " + lastName));
    }

}


