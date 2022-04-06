package com.isa.unasdziala.adapters;
import com.isa.unasdziala.domain.entities.Employee;
import com.isa.unasdziala.dto.EmployeeDto;

public class EmployeeAdapter {

    public EmployeeDto convertToEmployeeDto(Employee entityEmployee) {
        if (entityEmployee == null) {
            return null;
        }
        return EmployeeDto.builder().id(employee.id(),
                entityEmployee.getFirstName(),
                entityEmployee.getLastName(),
                entityEmployee.getContact(),
                entityEmployee.getAddress(),
                entityEmployee.getDepartment(),
                entityEmployee.getHolidays()
        );
    }

    public Employee convertToEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) {
            return null;
        }
        Employee employee = Employee.builder().id(employeeDto.getId())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .contact(employeeDto.getContact())
                .address(employeeDto.getAddress())
                .department(employeeDto.getDepartment())
                .holidays(employeeDto.getHolidays()) {
            return employee;
        }
}
