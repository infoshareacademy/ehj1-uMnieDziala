package com.isa.unasdziala.service;

import com.isa.unasdziala.model.Employee;
import com.isa.unasdziala.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll()
                .stream()
                .toList();
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

}
