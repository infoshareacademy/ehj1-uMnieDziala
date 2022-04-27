package com.isa.unasdziala.service;

import com.isa.unasdziala.exception.ResourceNotFoundException;
import com.isa.unasdziala.model.Employee;
import com.isa.unasdziala.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;


@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll()
                .stream()
                .toList();
    }

    public Employee findById(Long id){
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(format("Movie with id %d not found.", id)))
    }


    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeRequest employeeRequest) {
    var employee = getById(id);
    return updateEmployee(employee, employeeRequest);
    }
}
