package com.isa.unasdziala.adapters;

import com.isa.unasdziala.domain.entities.Employee;
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
                .holidays(employee.getHolidays());
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
                .holidays(employeeDto.getHolidays());
        return employee.build();
    }
}
