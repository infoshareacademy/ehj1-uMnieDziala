package com.isa.unasdziala.cli;

import com.isa.unasdziala.adapters.EmployeeAdapter;
import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.domain.entity.Employee;
import com.isa.unasdziala.domain.entity.Holiday;
import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.repository.EmployeesRepository;
import com.isa.unasdziala.repository.HolidayRepository;
import com.isa.unasdziala.repository.NonWorkingDaysRepository;
import com.isa.unasdziala.services.EmployeeService;
import com.isa.unasdziala.services.HolidayService;
import com.isa.unasdziala.services.NonWorkingDayService;
import com.isa.unasdziala.services.properties.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class HolidayCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");
    private final Logger log = LoggerFactory.getLogger(HolidayCli.class);

    private final Scanner scanner = new Scanner(System.in);

    private final HolidayService holidayService = new HolidayService(new HolidayRepository());
    private final NonWorkingDayService nonWorkingDayService = new NonWorkingDayService(new NonWorkingDaysRepository());
    private final EmployeeService employeeService = new EmployeeService(new EmployeesRepository(), employeeAdapter);
    private final Integer maxAbsence = new AppProperties().getMaxAbsence();
    private final EmployeeAdapter adapter = new EmployeeAdapter();
    private EmployeeDto employeeDto;
    private Employee employee;
    private Set<LocalDate> busyDays;
    private Set<LocalDate> freeDaysFromCalendar;
    private String firstName;
    private String lastName;


    public void run() {
        log.info("Run HolidayCli");
        logSTD.info("***************");
        logSTD.info("Choose employee");
        logSTD.info("***************");

        logSTD.info("Enter first name");
        firstName = scanner.nextLine();
        log.info("Get first name " + firstName);
        logSTD.info("Enter last name");
        lastName = scanner.nextLine();
        log.info("Get last name " + lastName);
        this.busyDays = new HashSet<>();
        this.freeDaysFromCalendar = new HashSet<>();
        try {
            log.info("Try to find employee {} in repository", firstName + " " + lastName);
            employeeDto = employeeService.findByFirstNameAndLastName(firstName, lastName);
        } catch (IllegalArgumentException e) {
            logSTD.error("Employee not exists in repository", e.getMessage());
            log.warn("Employee not exists in repository", e);
            return;
        }
        employee = adapter.convertToEmployee(employeeDto);

        logSTD.info("**************************************");
        logSTD.info("This employee has {} days off", employee.getHolidays());
        logSTD.info("**************************************");

        boolean again = true;
        while (again) {
            printMenu();
            int userOption = getUserOption();
            switch (userOption) {
                case 1 -> printBusyDays();
                case 2 -> checkDay();
                case 3 -> showHint();
                case 4 -> addNewHolidayToEmployee();
                case 0 -> again = false;
            }

        }
    }

    //Employee/id/holiday/{id}

    private void printMenu() {
        logSTD.info("--------------------------------------");
        logSTD.info("Options: ");
        logSTD.info("1. Show all busy days");
        logSTD.info("2. Check if day to holiday is correct");
        logSTD.info("3. Give me a hint about holiday days");
        logSTD.info("4. Add new holiday");
        logSTD.info("0. Back to previous menu");
        logSTD.info("--------------------------------------");
    }

    private int getUserOption() {
        log.info("Ask User for choose option");
        System.out.print("Choose option: ");
        String userOptionInput = scanner.nextLine();
        log.info("User choose option: " + userOptionInput);

        try {
            return Integer.parseInt(userOptionInput);
        } catch (Exception e) {
            return getUserOption();
        }
    }

    private void getAllBusyDays() {
        log.info("Get all busy days from all repository");
        Set<LocalDate> busyDayFromEvent = employee.getEvents().stream().map(e -> e.getDate()).collect(Collectors.toSet());
        Set<LocalDate> busyDaysFromNonWorkingDaysRepo = nonWorkingDayService.findAll().stream().map(Day::getDate).collect(Collectors.toSet());
        Set<LocalDate> busyDaysFromHolidayRepo = holidayService.findAll().stream().filter(holiday -> holiday.getEmployees().stream().collect(Collectors.toList()).size() >= maxAbsence).map(Holiday::getDate).collect(Collectors.toSet());
        Set<LocalDate> busyDaysFromEmployeeHolidaysDays = holidayService.findAll().stream().filter(holiday -> holiday.getEmployees().stream().anyMatch(e -> e.getId().equals(employee.getId()))).map(Holiday::getDate).collect(Collectors.toSet());

        busyDays.addAll(busyDaysFromNonWorkingDaysRepo);
        busyDays.addAll(busyDaysFromHolidayRepo);
        busyDays.addAll(busyDayFromEvent);
        busyDays.addAll(busyDaysFromEmployeeHolidaysDays);
        busyDays = busyDays.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        log.info("Get {} busy days", busyDays.size());
    }

    private void checkDay() {
        log.info("Start check day");
        logSTD.info("Please enter date to check");
        LocalDate dateToCheck = getInputDate();
        log.info("Day to check: ", dateToCheck.toString());
        getAllBusyDays();
        if (busyDays.contains(dateToCheck)) {
            logSTD.info("Date is busy. Choose another");
            log.info("Date is busy. Choose another");
        } else if (dateToCheck.getDayOfWeek() == DayOfWeek.SATURDAY || dateToCheck.getDayOfWeek() == DayOfWeek.SUNDAY) {
            logSTD.info("Date is weekend. Choose another");
            log.info("Date is weekend. Choose another");
        } else logSTD.info("Date is free");
        log.info("Date is free");
    }

    private void showHint() {
        log.info("Start show hint");
        logSTD.info("How many days show You ?");
        int userOption = getUserOption();
        freeDaysFromCalendar = LocalDate.now().datesUntil(LocalDate.now().with(lastDayOfYear()))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .collect(Collectors.toSet());
        getAllBusyDays();
        freeDaysFromCalendar.removeAll(busyDays);
        freeDaysFromCalendar = freeDaysFromCalendar.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        freeDaysFromCalendar = freeDaysFromCalendar.stream().limit(userOption).collect(Collectors.toSet());
        freeDaysFromCalendar = freeDaysFromCalendar.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        printFreeDays();
    }

    private void addNewHolidayToEmployee() {
        log.info("Start add new holiday to repository");
        int counter = 0;
        boolean isCorrect = false;
        LocalDate inputDate;
        getAllBusyDays();

        do {
            inputDate = getInputDate();
            log.info("Start try add day {} to repository", inputDate.toString());
            if (busyDays.contains(inputDate) || inputDate.getDayOfWeek() == DayOfWeek.SATURDAY || inputDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                logSTD.info("Day is busy or weekend. Choose another");
                log.info("Day is busy or weekend. Choose another");
                counter++;
            } else {
                isCorrect = true;
                break;
            }
        } while (!isCorrect && counter < 3);

        if (isCorrect) {
            EmployeeDto employeeDto = employeeService.findByFirstNameAndLastName(firstName, lastName);
            Employee employee = adapter.convertToEmployee(employeeDto);
            LocalDate finalInputDate = inputDate;
            if (employee.getHolidays() >= 1) {
                Holiday newHoliday = holidayService.addHoliday(inputDate, employee);
                logSTD.info("Adding new holiday {} to employee {}", newHoliday.getDate().toString(), employeeDto.getFirstName() + " " + employeeDto.getLastName());
                log.info("Adding new holiday {} to employee {}", newHoliday.getDate().toString(), employeeDto.getFirstName() + " " + employeeDto.getLastName());
            } else {
                logSTD.warn("WARNING!!!");
                logSTD.info("New holiday will not be added - Free holidays days count is to low!");
                log.info("New holiday will not be added - Free holidays days count is to low!");
                return;
            }
        } else logSTD.info("Try adding new day");
    }

    private void printBusyDays() {
        getAllBusyDays();
        logSTD.info("All busy days: ");
        for (LocalDate date : busyDays) {
            logSTD.info("\t" + date.toString());
        }
    }

    private LocalDate getInputDate() {
        log.info("Check to input data from User");
        logSTD.info("Enter date in format: yyyy-MM-dd");
        String dateInput = scanner.nextLine();

        try {
            return LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            logSTD.warn("Wrong date format!");
            log.warn("Wrong date format!");
            return getInputDate();
        }
    }

    private void printFreeDays() {
        getAllBusyDays();
        log.info("Print all free days");
        logSTD.info("Next free days: ");
        for (LocalDate date : freeDaysFromCalendar) {
            logSTD.info("\t" + date.toString());
        }
    }
}
