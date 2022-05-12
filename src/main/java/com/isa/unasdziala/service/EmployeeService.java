package com.isa.unasdziala.service;

import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.exception.ResourceNotFoundException;
import com.isa.unasdziala.model.Employee;
import com.isa.unasdziala.repository.EmployeeRepository;
import com.isa.unasdziala.request.EmployeeRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.format;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream().map(employee -> mapper.map(employee, EmployeeDto.class))
                .toList();

    }

    public EmployeeDto findById(Long id) {
        return mapper.map(findEmployeeById(id), EmployeeDto.class);
    }

    private Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(format("Employee with id %d not found.", id)));
    }


    public EmployeeDto addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = mapper.map(employeeRequest, Employee.class);
        return mapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeRequest employeeRequest) {
        Employee employee = findEmployeeById(id);
        Employee newEmployee = mapper.map(employeeRequest, Employee.class);

        updateEmployeeData(employee, newEmployee);
        employeeRepository.save(employee);

        return mapper.map(employee, EmployeeDto.class);
    }


    private void updateEmployeeData(Employee foundEmployee, Employee newEmployee) {
        foundEmployee.setFirstName(newEmployee.getFirstName());
        foundEmployee.setLastName(newEmployee.getLastName());
        foundEmployee.setAddress(newEmployee.getAddress());
        foundEmployee.setContact(newEmployee.getContact());
        foundEmployee.setDepartment(newEmployee.getDepartment());
        foundEmployee.setHolidays(newEmployee.getHolidays());

    }

    public void deleteById(Long id) {
        Employee employee = findEmployeeById(id);
        employee.getHolidayDays()
                .forEach(day -> day.getEmployees().remove(employee));
        employee.setHolidayDays(new HashSet<>());
        employeeRepository.delete(employee);
    }
}