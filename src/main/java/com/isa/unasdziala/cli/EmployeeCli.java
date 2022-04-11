package com.isa.unasdziala.cli;


import com.isa.unasdziala.domain.Address;
import com.isa.unasdziala.domain.Contact;
import com.isa.unasdziala.domain.Department;
import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.repository.EmployeesRepository;
import com.isa.unasdziala.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Arrays;
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
                case 3 -> addEmployee();
                case 4 -> updateEmployee();
                case 5 -> deleteEmployee();
                case 6 -> importFromFile();
                case 0 -> again = false;
            }
        }
    }

    private void addEmployee() {
        EmployeeDto newEmployeeDto = getNewEmployeeDto();
        try {
            EmployeeDto addedEmployeeDto = employeeService.addEmployee(newEmployeeDto);
            logSTD.info("Added: " + addedEmployeeDto.getFirstName() + " " + addedEmployeeDto.getLastName());

        } catch (IllegalArgumentException e) {
            logSTD.info(e.getMessage());
        }
    }

    private void updateEmployee() {
        logSTD.info("Enter employee first name");
        String firstName = scanner.nextLine();

        logSTD.info("Enter employee last name");
        String lastName = scanner.nextLine();

        EmployeeDto newEmployeeDto = getNewEmployeeDto();

        try {
            employeeService.updateEmployee(firstName, lastName, newEmployeeDto);
        } catch (IllegalArgumentException e) {
            logSTD.info("Wrong employee first and last name: " + firstName + " " + lastName);
        }

    }

    private EmployeeDto getNewEmployeeDto() {
        logSTD.info("Enter new first name");
        String newFirstName = scanner.nextLine();

        logSTD.info("Enter new last name");
        String newLastName = scanner.nextLine();

        logSTD.info("Enter new Phone Number");
        String newPhoneNo = scanner.nextLine();

        logSTD.info("Enter new Email Address");
        String newEmailAddress = scanner.nextLine();

        logSTD.info("Enter new City");
        String newCity = scanner.nextLine();

        logSTD.info("Enter new ZipCode");
        String newZipCode = scanner.nextLine();

        logSTD.info("Enter new Post Code");
        String newPostCode = scanner.nextLine();

        logSTD.info("Enter new Street");
        String newStreet = scanner.nextLine();

        logSTD.info("Enter new House Number");
        String newHouseNumber = scanner.nextLine();

        logSTD.info("Enter new Flat Number");
        String newFlatNumber = scanner.nextLine();

        logSTD.info("Enter new Departament name");
        Department newDepartament = getDepartmentName();

        logSTD.info("Enter new holidays amount");
        float newHolidays = Float.parseFloat(scanner.nextLine());

        EmployeeDto newEmployeeDto = new EmployeeDto(
                null,
                newFirstName,
                newLastName,
                new Contact(
                        newPhoneNo, newEmailAddress
                ),
                new Address(
                        newCity,
                        newZipCode,
                        newPostCode,
                        newStreet,
                        newHouseNumber,
                        newFlatNumber
                ),
                newDepartament,
                newHolidays,
                null
        );
        return newEmployeeDto;
    }

    private Department getDepartmentName() {
        List<String> departmentValues = Arrays.stream(Department.values()).map(Department::getDescription).toList();
        for (String value : departmentValues) {
            logSTD.info("\t" + value);
        }

        String departmentName = scanner.nextLine();
        if (departmentValues.contains(departmentName)) {
            return Department.fromString(departmentName);
        }
        logSTD.info("Wrong departament name");
        return getDepartmentName();
    }

    private void deleteEmployee() {
        logSTD.info("Delete");
        logSTD.info("Enter first name");
        String firstName = scanner.nextLine();

        logSTD.info("Enter last name");
        String lastName = scanner.nextLine();

        try {

        employeeService.deleteEmployee(firstName, lastName);
        } catch (IllegalArgumentException e) {
            logSTD.info(e.getMessage());
        }
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

        try {

            EmployeeDto employee = employeeService.findByFirstNameAndLastName(firstName, lastName);
            logSTD.info(employee.toString());
        } catch (IllegalArgumentException e) {
            logSTD.info(e.getMessage());
        }

    }

    private void importFromFile() {
        logSTD.info("Loading from file...");
        employeeService.importFile();
    }

    private void printMenu() {
        logSTD.info("-----------------------------------------------");
        logSTD.info("Options: ");
        logSTD.info("1. Show all Employees");
        logSTD.info("2. Show Employee by first and last name");
        logSTD.info("3. Add new Employee");
        logSTD.info("4. Update Employee by first and last name");
        logSTD.info("5. Delete employee by first and last name");
        logSTD.info("6. Import from employees_repository.csv file");
        logSTD.info("0. Back to previous menu");
        logSTD.info("-----------------------------------------------");
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
