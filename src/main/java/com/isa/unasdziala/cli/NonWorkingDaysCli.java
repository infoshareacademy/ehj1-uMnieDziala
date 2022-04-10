package com.isa.unasdziala.cli;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.repository.NonWorkingDaysRepository;
import com.isa.unasdziala.services.NonWorkingDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class NonWorkingDaysCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");

    private final Scanner scanner = new Scanner(System.in);
    private final NonWorkingDayService service = new NonWorkingDayService(new NonWorkingDaysRepository());

    public void run() {
        boolean again = true;

        while (again) {
            printMenu();
            int userOption = getUserOption();
            switch (userOption) {
                case 1 -> printAllDays();
                case 2 -> printDayByDate();
                case 3 -> addNewDay();
                case 4 -> updateDay();
                case 5 -> deleteDay();
                case 0 -> again = false;
            }
        }
    }

    private void printMenu() {
        logSTD.info("Options: ");
        logSTD.info("1. Show all Non-Working Days");
        logSTD.info("2. Show Non-Working Days by date");
        logSTD.info("3. Add new Non-working Day");
        logSTD.info("4. Update Non-working Day by date");
        logSTD.info("5. Delete Non-working Day by date");
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

    public void printAllDays() {
        List<Day> days = service.findAll();
        days.forEach(day -> logSTD.info(day.toString()));
    }

    public void printDayByDate() {
        LocalDate date = getInputDate();

        try {
            Day dayByDate = service.findDayByDate(date);
            logSTD.info(dayByDate.toString());
        } catch (IllegalArgumentException e) {
            logSTD.info(e.getMessage());
        }
    }

    public void addNewDay() {
        LocalDate date = getInputDate();

        logSTD.info("Enter holiday name");
        String name = scanner.nextLine();

        logSTD.info("Enter holiday description");
        String desc = scanner.nextLine();

        logSTD.info("Enter country symbol");
        String country = scanner.nextLine();

        Day day = new Day(date, name, desc, country);

        try {
            Day addedDay = service.add(day);
            logSTD.info("Added " + addedDay);
        } catch (IllegalArgumentException e) {
            logSTD.info(e.getMessage());
        }
    }

    public void deleteDay() {
        LocalDate date = getInputDate();

        try {
            Day deletedDay = service.deleteByDate(date);
            logSTD.info("Deleted: " + deletedDay.toString());
        } catch (IllegalArgumentException e) {
            logSTD.info(e.getMessage());
        }
    }

    public void updateDay() {
        LocalDate date = getInputDate();

        logSTD.info("Enter new day name");
        String name = scanner.nextLine();

        logSTD.info("Enter new day description");
        String desc = scanner.nextLine();

        Day newDay = new Day(date, name, desc);

        try {
            Day updatedDay = service.updateByDate(newDay);
            logSTD.info("Updated: " + updatedDay.toString());
        } catch (IllegalArgumentException e) {
            logSTD.info(e.getMessage());
        }
    }

    private LocalDate getInputDate() {
        logSTD.info("Enter date in format: yyyy/MM/dd");
        String dateInput = scanner.nextLine();

        try {
            return LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (DateTimeParseException e) {
            logSTD.info("Wrong date format!");
            return getInputDate();
        }
    }
}