package com.isa.unasdziala.cli;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.repository.NonWorkingDaysRepository;
import com.isa.unasdziala.services.NonWorkingDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class NonWorkingDaysCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");

    private final Scanner scanner = new Scanner(System.in);
    private final NonWorkingDayService service = new NonWorkingDayService(new NonWorkingDaysRepository());

    public void printAllDays() {
        List<Day> days = service.findAll();
        days.forEach(day -> logSTD.info(day.toString()));
    }

    public void printDayByDate() {
        logSTD.info("Enter date in format: yyyy/MM/dd");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        try {
            Day dayByDate = service.findDayByDate(date);
            logSTD.info(dayByDate.toString());
        } catch (IllegalArgumentException e) {
            logSTD.info("Sorry, wrong data type or there is no Non-Working Day with given date");
        }
    }

    public void addNewDay() {
        logSTD.info("Enter date in format: yyyy/MM/dd");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        logSTD.info("Enter holiday name");
        String name = scanner.nextLine();

        // date name desc country
        logSTD.info("Enter holiday description");
        String desc = scanner.nextLine();

        logSTD.info("Enter country symbol");
        String country = scanner.nextLine();

        Day day = new Day(date, name, desc, country);
        service.add(day);
        logSTD.info("Added " + day);
    }

    public void deleteDay() {
        logSTD.info("Enter date in format: yyyy/MM/dd");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        Day deletedDay = service.deleteByDate(date);
        logSTD.info("Deleted: " + deletedDay.toString());
    }

    public void updateDay() {
        logSTD.info("Enter date in format: yyyy/MM/dd");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        logSTD.info("Enter holiday name");
        String name = scanner.nextLine();

        // date name desc country
        logSTD.info("Enter holiday description");
        String desc = scanner.nextLine();

        Day newDay = new Day(date, name, desc);

        Day updatedDay = service.updateByDate(newDay);
        logSTD.info("Updated: " + updatedDay.toString());
    }
}
