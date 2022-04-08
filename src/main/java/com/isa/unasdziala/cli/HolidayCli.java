package com.isa.unasdziala.cli;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.domain.Holiday;
import com.isa.unasdziala.repository.HolidayRepository;
import com.isa.unasdziala.repository.NonWorkingDaysRepository;
import com.isa.unasdziala.services.HolidayService;
import com.isa.unasdziala.services.NonWorkingDayService;
import com.isa.unasdziala.services.properties.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfYear;

public class HolidayCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");

    private final Scanner scanner = new Scanner(System.in);

    private final HolidayService holidayService = new HolidayService(new HolidayRepository());
    private final NonWorkingDayService nonWorkingDayService = new NonWorkingDayService(new NonWorkingDaysRepository());
    //    private final EmployeesService employeesService = new EmployeesService(new EmployeesRepository());
    private Integer maxAbsence = new AppProperties().getMaxAbsence();

    public void run() {
        boolean again = true;

        while (again) {
            printMenu();
            int userOption = getUserOption();
            switch (userOption) {
                case 1 -> printAllFreeDays();
//                case 2 -> service.addEmployee();
//                case 3 -> service.updateEmployeeByLastName();
                case 0 -> again = false;
            }

        }
    }

    private void printMenu() {
        logSTD.info("Options: ");
        logSTD.info("1. Show all free days");
        logSTD.info("2. Show recommended holiday days");
        logSTD.info("3. Add new holiday");
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

    private void printAllFreeDays() {
        List<LocalDate> freeDays = new ArrayList<>();
        List<LocalDate> freeDaysFromCalendar = LocalDate.now().datesUntil(LocalDate.now().with(lastDayOfYear()))
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SATURDAY)
                .filter(date -> date.getDayOfWeek() != DayOfWeek.SUNDAY)
                .collect(Collectors.toList());
        List<LocalDate> busyDaysFromNonWorkingDaysRepo = nonWorkingDayService.findAll().stream()
                .map(Day::getDate)
                .collect(Collectors.toList());
        List<LocalDate> busyDaysFromHolidayRepo = holidayService.findAll().stream()
                .filter(holiday -> holiday.getEmployees().stream().collect(Collectors.toList()).size() > maxAbsence)
                .map(Holiday::getDate)
                .collect(Collectors.toList());

        freeDays.addAll(freeDaysFromCalendar);
        freeDays.removeAll(busyDaysFromNonWorkingDaysRepo);
        freeDays.removeAll(busyDaysFromHolidayRepo);
        freeDays.removeAll(busyDaysFromHolidayRepo);
    }
}
