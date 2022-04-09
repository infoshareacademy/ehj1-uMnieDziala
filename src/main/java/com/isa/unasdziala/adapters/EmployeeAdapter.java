package com.isa.unasdziala.adapters;

import com.isa.unasdziala.domain.EmployeeCSV;
import com.isa.unasdziala.domain.entity.Employee;
import com.isa.unasdziala.dto.EmployeeDto;

public class EmployeeAdapter {

    public EmployeeDto convertToEmployeeDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .contact(employee.getContact())
                .address(employee.getAddress())
                .department(employee.getDepartment())
                .holidays(employee.getHolidays())
                .events(employee.getEvents())
                .build();
        return employeeDto;
    }

    public Employee convertToEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return null;
        }
        Employee employee = new Employee();
                employee.setId(employeeDto.getId());
                employee.setFirstName(employeeDto.getFirstName());
                employee.setLastName(employeeDto.getLastName());
                employee.setContact(employeeDto.getContact());
                employee.setAddress(employeeDto.getAddress());
                employee.setDepartment(employeeDto.getDepartment());
                employee.setHolidays(employeeDto.getHolidays());
                employee.setEvents(employeeDto.getEvents());
        return employee;
    }

    public EmployeeDto convertEmployeeCSVToEmployeeDto(EmployeeCSV employeeCSV) {
        if (employeeCSV == null) {
            return null;
        }
        EmployeeDto employeeDto = EmployeeDto.builder()
                .firstName(employeeCSV.getFirstName())
                .lastName(employeeCSV.getLastName())
                .contact(employeeCSV.getContact())
                .address(employeeCSV.getAddress())
                .department(employeeCSV.getDepartment())
                .holidays(employeeCSV.getHolidays())
                .events(employeeCSV.getEvents())
                .build();
        return employeeDto;
    }
}