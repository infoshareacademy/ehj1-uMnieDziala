package com.isa.unasdziala.cli;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.services.EmployeeService;
import com.isa.unasdziala.services.NonWorkingDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");

    private final Scanner scanner = new Scanner(System.in);
    private final EmpleyeeService service = new EmpleyeeService(new EmpleyeeService());

    public void run() {
        boolean again = true;

        while (again) {
            printMenu();
            int userOption = getUserOption();
            switch (userOption) {
                case 1 -> findAllEmployees();
                case 2 -> findEmployeeByLastName();
                //TO DO
                //case 3 -> service.addEmployee();
                //case 4 -> service.updateEmployeeByLastName();
                case 5 -> deleteEmployeeByLastName();
                case 0 -> again = false;
            }
        }
    }

    private void printMenu() {
        logSTD.info("Options: ");
        logSTD.info("1. Show all employees");
        logSTD.info("2. Search employee by lastname");
        logSTD.info("3. Add new employee");
        logSTD.info("4. Update employee data");
        logSTD.info("5. Remove employee");
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

    public String getInputEmployeeLastname() {

        logSTD.info("Enter employee last name:");
        String input = scanner.nextLine();
        return input;
    }

    public void findAllEmployees() {
        logSTD.info("List of employees:");
        //service.findAllEmployees();
    }

    public void findEmployeeByLastName() {
        String userInput = getInputEmployeeLastname();
        logSTD.info("Search results for employee lastname: {} ", userInput);
        //service.findEmployeeByLastName(getUserInput());
    }

    public void deleteEmployeeByLastName() {
        String userInput = getInputEmployeeLastname();
        logSTD.info("Deleting employee : {} ", userInput);
        //service.deleteEmployeeByLastName(getUserInput());

    }

}

