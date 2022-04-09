package com.isa.unasdziala.cli;


import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.repository.EmployeesRepository;
import com.isa.unasdziala.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Scanner;

public class EmployeeCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");

    private final EmployeeService employeeService = new EmployeeService(new EmployeesRepository());
    private final Scanner scanner = new Scanner(System.in);


    public void run() {
        boolean again = true;

        while (again) {
            printMenu();
            int userOption = getUserOption();
            switch (userOption) {
                case 1 -> showAllEmployees();
                case 2 -> showEmployeeDetailsByFirstAndLastName();
                case 4 -> deleteEmployee();
                case 5 -> importFromFile();
                case 0 -> again = false;
            }
        }
    }

    private void deleteEmployee() {
        logSTD.info("Delete");
        logSTD.info("Enter first name");
        String firstName = scanner.nextLine();

        logSTD.info("Enter last name");
        String lastName = scanner.nextLine();

        employeeService.deleteEmployee(firstName, lastName);
    }

    private void showAllEmployees() {
        List<EmployeeDto> allEmployees = employeeService.findAll();

        logSTD.info("All employees: ");
        for (EmployeeDto employee : allEmployees) {
            logSTD.info("\t" + employee.getFirstName() + " " + employee.getLastName());
        }
    }

    private void showEmployeeDetailsByFirstAndLastName() {
        logSTD.info("Enter first name");
        String firstName = scanner.nextLine();

        logSTD.info("Enter last name");
        String lastName = scanner.nextLine();

        EmployeeDto employee = employeeService.findByFirstNameAndLastName(firstName, lastName);
        logSTD.info(employee.toString());

    }

    private void importFromFile() {
        logSTD.info("Loading from file...");
        employeeService.importFile();
    }


    private void printMenu() {
        logSTD.info("Options: ");
        logSTD.info("1. Show all Employees");
        logSTD.info("2. Show Employee by first name and last name");
        logSTD.info("5. Import from employees_repository.csv file");
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
}
