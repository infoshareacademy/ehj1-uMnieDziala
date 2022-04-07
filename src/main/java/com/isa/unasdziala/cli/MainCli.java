package com.isa.unasdziala.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");
    private final EmployeeCli employeeCli = new EmployeeCli();
    private final NonWorkingDaysCli nonWorkingDaysCli = new NonWorkingDaysCli();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        introduce();
        while (true) {
            printMenu();
            int userOption = getUserOption();
            switch (userOption) {
                case 1 -> employeeCli.printAllEmployees();
                case 4 -> nonWorkingDaysCli.printAllDays();
                case 5 -> nonWorkingDaysCli.printDayByDate();
                case 6 -> nonWorkingDaysCli.addNewDay();
                case 7 -> nonWorkingDaysCli.updateDay();
                case 8 -> nonWorkingDaysCli.deleteDay();
                case 0 -> System.exit(-1);
            }
        }
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

    private void introduce() {
        logSTD.info("+---------------------------------------+");
        logSTD.info("|              Welcome to               |");
        logSTD.info("|     Employee Leave Management App     |");
        logSTD.info("+---------------------------------------+");
    }

    private void printMenu() {
        logSTD.info("Options: ");
        logSTD.info("1. Show all Employees");
        logSTD.info("4. Show all Non-Working Days");
        logSTD.info("5. Show Non-Working Days by date");
        logSTD.info("6. Add new Non-working Day");
        logSTD.info("7. Update Non-working Day by date");
        logSTD.info("8. Delete Non-working Day by date");
        logSTD.info("0. Exit");
    }
}
