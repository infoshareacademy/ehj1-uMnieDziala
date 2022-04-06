package com.isa.unasdziala.adapters;
import com.isa.unasdziala.domain.entities.Employee;
import com.isa.unasdziala.dto.EmployeeDto;

public class EmployeeAdapter {

    public EmployeeDto convertToEmployeeDto(Employee entityEmployee) {
        if (entityEmployee == null) {
            return null;
        }
        return new EmployeeDto(entityEmployee.getId(),
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
        Employee employee = new Employee(employeeDto.id(),
                employeeDto.firstName(),
                employeeDto.lastName(),
                employeeDto.contact(),
                employeeDto.address(),
                employeeDto.department(),
                employeeDto.holidays());
        return employee;

    }
}
