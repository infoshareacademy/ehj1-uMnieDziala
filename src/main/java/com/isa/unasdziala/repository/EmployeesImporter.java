package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Employee;
import com.isa.unasdziala.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import javax.persistence.EntityManager;

public class EmployeesImporter {
    private static final Path PATH_TO_CSV = Paths.get("src", "main", "resources", "employees_repository.csv");
    private static final char CSV_SEPARATOR = ';';
    private static final Logger logger = LoggerFactory.getLogger(EmployeesImporter.class);

    private List<Employee> employees;

    public EmployeesImporter() {
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

        } catch (IOException e) {
            logger.error("Can't load csv file", e);
        }
        return employees;
    }
}