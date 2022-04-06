package com.isa.unasdziala.repository;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.isa.unasdziala.domain.Employee;
import com.isa.unasdziala.utils.CalendarLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

public class EmployeesRepository {
    ClassLoader classLoader = getClass().getClassLoader();
    private final String CSV_FILE_NAME = "employees_repository.csv";
    private final File CSV_FILE = new File(classLoader.getResource(CSV_FILE_NAME).getFile());
    private static final Path PATH_TO_CSV = Paths.get("src", "main", "resources", "employees_repository.csv");
    private static final char CSV_SEPARATOR = ';';
    private static final Logger log = LoggerFactory.getLogger(EmployeesRepository.class);
    private final CalendarLoader calendarLoader = new CalendarLoader();

    private List<Employee> employees;

    public EmployeesRepository() {
        log.info("Creating employee repository");
        if (this.employees == null) {
            this.employees = importEmployees();
        }
    }

    List<Employee> importEmployees() {
        log.info("Start read file: {}", CSV_FILE_NAME);
        List<Employee> employees = new ArrayList<>();
        try (FileReader fileReader = new FileReader(CSV_FILE)) {
            employees = new CsvToBeanBuilder<Employee>(fileReader)
                    .withType(Employee.class)
                    .withSeparator(CSV_SEPARATOR)
                    .withSkipLines(1)
                    .build()
                    .parse();

            log.info("Have been loaded: {} employees", employees.size());
            for (Employee e : employees) {
                e.setEvents(calendarLoader.loadEmployeeEventCalendar(e));
            }

        } catch (StreamReadException e) {
            log.error("Error while reading non working days file, " + e.getMessage());
        } catch (IOException e) {
            log.error("File read error, " + e.getMessage());
        }
        return employees;
    }
}