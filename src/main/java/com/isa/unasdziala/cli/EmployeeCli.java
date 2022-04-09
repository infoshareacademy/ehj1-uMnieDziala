package com.isa.unasdziala.cli;

import com.isa.unasdziala.domain.*;
//import com.isa.unasdziala.services.EmployeeService;
import com.isa.unasdziala.services.NonWorkingDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.isa.unasdziala.domain.Department.*;

import static com.isa.unasdziala.domain.Department.*;

public class EmployeeCli {
    private static final Logger logSTD = LoggerFactory.getLogger("STDOUT");
    Department departmentInput;
    private final Scanner scanner = new Scanner(System.in);
    //private final EmpleyeeService service = new EmpleyeeService(new EmpleyeeService());

    public void run() {
        boolean again = true;

        while (again) {
            printMenu();
            int userOption = getUserOption();
            switch (userOption) {
                case 1 -> findAllEmployees();
                case 2 -> findEmployeeByLastName();
                case 3 -> addNewEmployee();
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

    public void addNewEmployee() {
        logSTD.info("Enter employee name:");
        String name = scanner.nextLine();
        logSTD.info("Enter employee lastname:");
        String lastname = scanner.nextLine();
        logSTD.info("Enter employee phone number:");
        String phone = scanner.nextLine();
        logSTD.info("Enter employee email address:");
        String email = scanner.nextLine();
        logSTD.info("Enter employee city:");
        String city = scanner.nextLine();
        logSTD.info("Enter employee zip code:");
        String code = scanner.nextLine();
        logSTD.info("Enter employee post city:");
        String postCity = scanner.nextLine();
        logSTD.info("Enter employee street:");
        String street = scanner.nextLine();
        logSTD.info("Enter employee house number:");
        String houseNumber = scanner.nextLine();
        logSTD.info("Enter employee flat number:");
        String flatnumber = scanner.nextLine();
        logSTD.info("Enter employee department:");
        getDepartmentInput();
        logSTD.info("Enter employee holidays:");
        Float holidays = Float.valueOf(scanner.nextLine());

        Address address = new Address(city, code, postCity, street, houseNumber, flatnumber);
        Contact contact = new Contact(phone, email);

        Employee employee = new Employee(name, lastname, contact, address,departmentInput, holidays);
        System.out.println(employee);

        //service.addEmployee(employee);
    }

    public void getDepartmentInput() {
        int i = 0;
        for (Department department : VALUES) {
            i++;
            logSTD.info(i + ". " + department.fromDepartment(department));
        }
            int userOption = getUserOption();
            switch (userOption) {

                case 1 -> departmentInput = ACTUARIAL_DEPARTMENT;
                case 2 -> departmentInput = SALES_DEPARTAMENT;
                case 3 -> departmentInput = FINANCIAL_DEPARTMENT;
            }
        }

}






