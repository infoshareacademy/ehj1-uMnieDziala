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

public class HolidayCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");

    private final Scanner scanner = new Scanner(System.in);

    private final HolidayService holidayService = new HolidayService(new HolidayRepository());
    private final NonWorkingDayService nonWorkingDayService = new NonWorkingDayService(new NonWorkingDaysRepository());
    private final EmployeeService employeeService = new EmployeeService(new EmployeesRepository());
    private final Integer maxAbsence = new AppProperties().getMaxAbsence();
    private final EmployeeAdapter adapter = new EmployeeAdapter();
    private Set<LocalDate> busyDays = new HashSet<>();


    public void run() {
        this.busyDays = new HashSet<>();
        boolean again = true;

        while (again) {
            printMenu();
            int userOption = getUserOption();
            switch (userOption) {
                case 1 -> printBusyDays();
                case 2 -> checkDay();
                case 4 -> addNewHolidayToEmployee();
                case 0 -> again = false;
            }

        }
    }

    private void printMenu() {
        logSTD.info("Options: ");
        logSTD.info("1. Show all busy days for employee");
        logSTD.info("2. Check if day to holiday is correct");
        logSTD.info("3. Give me a hint about holiday days");
        logSTD.info("4. Add new holiday");
        logSTD.info("0. Back to previous menu");
    }

    private int getUserOption() {
        System.out.print("Choose option: ");
        String userOptionInput = scanner.nextLine();

        try {
            return Integer.parseInt(userOptionInput);
        } catch (Exception e) {
            return getUserOption();
        }
    }

    private void getAllBusyDays() {
        logSTD.info("Enter first name");
        String firstName = scanner.nextLine();

        logSTD.info("Enter last name");
        String lastName = scanner.nextLine();

        EmployeeDto employeeDto = employeeService.findByFirstNameAndLastName(firstName, lastName);
        Employee employee = adapter.convertToEmployee(employeeDto);

        Set<LocalDate> busyDayFromEvent = employee.getEvents().stream()
                .map(e -> e.getDate())
                .collect(Collectors.toSet());
        Set<LocalDate> busyDaysFromNonWorkingDaysRepo = nonWorkingDayService.findAll().stream()
                .map(Day::getDate)
                .collect(Collectors.toSet());
        Set<LocalDate> busyDaysFromHolidayRepo = holidayService.findAll().stream()
                .filter(holiday -> holiday.getEmployees().stream().collect(Collectors.toList()).size() >= maxAbsence)
                .map(Holiday::getDate)
                .collect(Collectors.toSet());
        Set<LocalDate> busyDaysFromEmployeHolidaysDays = holidayService.findAll().stream()
                .filter(h -> h.getEmployees().contains(employee))
                .map(Holiday::getDate)
                .collect(Collectors.toSet());

        busyDays.addAll(busyDaysFromNonWorkingDaysRepo);
        busyDays.addAll(busyDaysFromHolidayRepo);
        busyDays.addAll(busyDayFromEvent);
        busyDays.addAll(busyDaysFromEmployeHolidaysDays);
        busyDays = busyDays.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        logSTD.info(busyDaysFromNonWorkingDaysRepo.toString());
        logSTD.info(busyDaysFromHolidayRepo.toString());
        logSTD.info(busyDaysFromEmployeHolidaysDays.toString());
        logSTD.info(busyDayFromEvent.toString());
    }


    private void checkDay() {
        logSTD.info("Please enter date to check");
        LocalDate dateToCheck = getInputDate();
        if (busyDays.contains(dateToCheck)) {
            logSTD.info("Date is busy. Choose another");
        } else if (dateToCheck.getDayOfWeek() == DayOfWeek.SATURDAY || dateToCheck.getDayOfWeek() == DayOfWeek.SUNDAY) {
            logSTD.info("Date is weekend. Choose another");
        } else logSTD.info("Date is free");
    }

    private void addNewHolidayToEmployee() {
        logSTD.info("Enter first name");
        String firstName = scanner.nextLine();

        logSTD.info("Enter last name");
        String lastName = scanner.nextLine();

        int counter = 0;
        boolean isCorrect = false;
        LocalDate inputDate;
        do {
            inputDate = getInputDate();
            if (busyDays.contains(inputDate) || inputDate.getDayOfWeek() == DayOfWeek.SATURDAY || inputDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                logSTD.info("Day is busy or weekend. Choose another");
                counter++;
            } else {
                isCorrect = true;
                break;
            }
        }
        while (!isCorrect && counter < 3);

        if (isCorrect) {
            EmployeeDto employeeDto = employeeService.findByFirstNameAndLastName(firstName, lastName);
            Employee employee = adapter.convertToEmployee(employeeDto);
            LocalDate finalInputDate = inputDate;

            Holiday newHoliday = holidayService.addHoliday(inputDate, employee);
            logSTD.info("Adding new holiday {} to employee {}", newHoliday.getDate().toString(), employeeDto.getFirstName() + " " + employeeDto.getLastName());
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
        logSTD.info("Enter date in format: yyyy-MM-dd");
        String dateInput = scanner.nextLine();

        try {
            return LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            logSTD.info("Wrong date format!");
            return getInputDate();
        }
    }

}
