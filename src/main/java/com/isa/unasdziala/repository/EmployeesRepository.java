package com.isa.unasdziala.repository;

import com.isa.unasdziala.adapters.EmployeeAdapter;
import com.isa.unasdziala.domain.EmployeeCSV;
import com.isa.unasdziala.domain.entities.Employee;
import com.isa.unasdziala.domain.entities.Holiday;
import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.utils.CalendarLoader;
import com.isa.unasdziala.utils.HibernateUtil;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class EmployeesRepository {
    private static final Path PATH_TO_CSV = Paths.get("src", "main", "resources", "employees_repository.csv");
    private static final char CSV_SEPARATOR = ';';
    private static final Logger logger = LoggerFactory.getLogger(EmployeesRepository.class);

    private final CalendarLoader calendarLoader = new CalendarLoader();
    private final EntityManager em = HibernateUtil.getEntityManager();

    private EmployeeAdapter adapter = new EmployeeAdapter();

    public EmployeesRepository() {
        logger.debug("Creating employee repository");
    }

    public List<EmployeeDto> findAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class)
                .getResultStream()
                .map(adapter::convertToEmployeeDto)
                .toList();
    }

    public Optional<EmployeeDto> findByFirstNameAndLastName(String firstName, String lastName) {
        return em.createNamedQuery("Employee.findByFirstNameAndLastName", Employee.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultStream()
                .map(adapter::convertToEmployeeDto)
                .findFirst();
    }

    public Optional<EmployeeDto> add(EmployeeDto employeeDto) {
        Optional<EmployeeDto> existingEmployee = findByFirstNameAndLastName(employeeDto.getFirstName(), employeeDto.getLastName());
        if (existingEmployee.isPresent()) {
            return Optional.empty();
        }
        Employee employee = adapter.convertToEmployee(employeeDto);
        em.persist(employee);
        return Optional.of(adapter.convertToEmployeeDto(employee));
    }

    public Optional<EmployeeDto> update(EmployeeDto newEmployeeDto) {
        Optional<EmployeeDto> employeeDtoOptional = findByFirstNameAndLastName(newEmployeeDto.getFirstName(), newEmployeeDto.getLastName());
        if (employeeDtoOptional.isPresent()) {
            EmployeeDto employeeDto = employeeDtoOptional.get();
            Employee employee = adapter.convertToEmployee(employeeDto);

            employee.setFirstName(newEmployeeDto.getFirstName());
            employee.setLastName(newEmployeeDto.getLastName());
            employee.setAddress(newEmployeeDto.getAddress());
            employee.setContact(newEmployeeDto.getContact());
            employee.setDepartment(newEmployeeDto.getDepartment());

            em.merge(employee);
            return Optional.of(adapter.convertToEmployeeDto(employee));
        }
        return Optional.empty();
    }

    public Optional<EmployeeDto> delete(String firstName, String lastName) {
        Optional<EmployeeDto> employeeDtoOptional = findByFirstNameAndLastName(firstName, lastName);
        if (employeeDtoOptional.isPresent()) {
            Employee employee = adapter.convertToEmployee(employeeDtoOptional.get());
            em.remove(employee);
            return Optional.of(adapter.convertToEmployeeDto(employee));
        }
        return Optional.empty();
    }


    public void importEmployees() {
        logger.debug("Importing employees from file.");
        try (FileReader fileReader = new FileReader(PATH_TO_CSV.toString())) {
            List<EmployeeCSV> employeesCSV = new CsvToBeanBuilder<EmployeeCSV>(fileReader)
                    .withType(EmployeeCSV.class)
                    .withSeparator(CSV_SEPARATOR)
                    .withSkipLines(1)
                    .build()
                    .parse();
            List<EmployeeDto> employeesDto = employeesCSV.stream().map(adapter::convertEmployeeCSVToEmployeeDto).toList();
            for (EmployeeDto employeeDto : employeesDto) {
                employeeDto.setEvents(calendarLoader.loadEmployeeEventCalendar(employeeDto));
                add(employeeDto);
            }

        } catch (IOException e) {
            logger.warn("Can't load csv file", e);
        }
    }

    public void addHoliday(EmployeeDto employee, Holiday holiday) {
        Optional<EmployeeDto> employeeDtoOptional = findByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());
        adapter.convertToEmployee(employeeDtoOptional.get()).getHolidayDays().add(holiday);
        em.merge(adapter.convertToEmployee(employeeDtoOptional.get()));
    }
}