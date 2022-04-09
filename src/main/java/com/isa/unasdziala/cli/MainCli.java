package com.isa.unasdziala.cli;

import com.isa.unasdziala.repository.EmployeesRepository;
import com.isa.unasdziala.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");
    private final NonWorkingDaysCli nonWorkingDaysCli = new NonWorkingDaysCli();
    private final EmployeeCli employeeCli = new EmployeeCli();

    private final EmployeeService service = new EmployeeService(new EmployeesRepository());
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        introduce();
        while (true) {
            printMenu();
            int userOption = getUserOption();
            switch (userOption) {
                case 1 -> employeeCli.run();
                case 2 -> nonWorkingDaysCli.run();
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
        logSTD.info("1. Employee Management");
        logSTD.info("2. Non Working Days Management");
        logSTD.info("3. Holidays Management");
        logSTD.info("0. Exit");
    }
}
