package com.isa.unasdziala.adapters;

import com.isa.unasdziala.domain.EmployeeCSV;
import com.isa.unasdziala.domain.entity.Employee;
import com.isa.unasdziala.dto.EmployeeDto;

public class EmployeeAdapter {

    public EmployeeDto convertToEmployeeDto(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDto.EmployeeDtoBuilder employeeDto = EmployeeDto.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .contact(employee.getContact())
                .address(employee.getAddress())
                .department(employee.getDepartment())
                .holidays(employee.getHolidays())
                .events(employee.getEvents());
        return EmployeeDto.builder().build();
    }

    public Employee convertToEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return null;
        }
        Employee.EmployeeBuilder employee = Employee.builder()
                .id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .contact(employeeDto.getContact())
                .address(employeeDto.getAddress())
                .department(employeeDto.getDepartment())
                .holidays(employeeDto.getHolidays())
                .events(employeeDto.getEvents());
        return employee.build();
    }

    public EmployeeDto convertEmployeeCSVToEmployeeDto(EmployeeCSV employeeCSV) {
        if (employeeCSV == null) {
            return null;
        }
        EmployeeDto.EmployeeDtoBuilder employeeDto = EmployeeDto.builder()
                .firstName(employeeCSV.getFirstName())
                .lastName(employeeCSV.getLastName())
                .contact(employeeCSV.getContact())
                .address(employeeCSV.getAddress())
                .department(employeeCSV.getDepartment())
                .holidays(employeeCSV.getHolidays())
                .events(employeeCSV.getEvents());
        return EmployeeDto.builder().build();
    }
}