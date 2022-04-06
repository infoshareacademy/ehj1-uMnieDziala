package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public class EmployeesRepository {
    private static final Path PATH_TO_CSV = Paths.get("src", "main", "resources", "employees_repository.csv");
    private static final char CSV_SEPARATOR = ';';
    private static final Logger logger = LoggerFactory.getLogger(EmployeesRepository.class);

    private List<Employee> employees;

    public EmployeesRepository() {
        logger.debug("Creating employee repository");
        this.employees = importEmployees();
    }

    public static List<Employee> importEmployees() {

        logger.debug("Importing employees from file.");
        List<Employee> employees = new ArrayList<>();

        try (FileReader fileReader = new FileReader(PATH_TO_CSV.toString())) {
            employees = new CsvToBeanBuilder<Employee>(fileReader)
                    .withType(Employee.class)
                    .withSeparator(CSV_SEPARATOR)
                    .withSkipLines(1)
                    .build()
                    .parse();
            for (Employee e : employees) {
                e.setEvents(calendarLoader.loadEmployeeEventCalendar(e));
            }

        } catch (IOException e) {
            logger.error("Can't load csv file", e);
        }
        return employees;
    }

    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public static Optional<Employee> findEmployeeByLastName(String lastName) {
        return employees
                .stream()
                .filter(employee -> employee.getLastName().equals(lastName))
                .findFirst();
    }

    public List<Employee> findAllEmployees() {
        return List.copyOf(employees);
    }

    public Optional<Employee> updateEmployeeByLastName(String lastName, Employee newEmployee) {
        Optional<Employee> optionalEmployee = findEmployeeByLastName(lastName);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setFirstName(newEmployee.getFirstName());
            employee.setLastName(newEmployee.getLastName());
            employee.setAddress(newEmployee.getAddress());
        }
        return optionalEmployee;
    }

    public Optional<Employee> deleteEmployeeByLastName(String lastName) {
        Optional<Employee> employee = findEmployeeByLastName(lastName);
        employee.ifPresent(employees::remove);
        return employee;
    }

    String pathToEmployeesCSVFile() {
        return EmployeesRepository.class.getClassLoader().getResource(String.valueOf(EMPLOYEES_CSV_FILE_NAME)).getPath();
    }
}