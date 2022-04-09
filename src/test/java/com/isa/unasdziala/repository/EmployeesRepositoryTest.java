//package com.isa.unasdziala.repository;
//import com.isa.unasdziala.domain.*;
//import org.junit.jupiter.api.Test;
//import static org.assertj.core.api.Assertions.assertThat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//class EmployeesRepositoryTest {
//    private EmployeesRepository sut = new EmployeesRepository();
//
//    @Test
//    void shouldContainsOnlyTestEmployeesFromFile() {
//
//        List<Employee> refferenceEmployees = getRefferenceEmployees();
//
//        List<Employee> result = sut.importEmployees();
//
//        assertThat(refferenceEmployees).containsExactlyElementsOf(result);
//    }
//
//    private List<Employee> getReferenceEmployees() {
//
//        Address address1 = new Address("Bylowo-Leśnictwo", "80-002", "Kartuzy", "", "5", "");
//        Address address2 = new Address("Gdynia", "80-001", "Gdynia", "Starowiejska", "5", "33");
//        Address address3 = new Address("Gdańsk", "80-003", "Gdańsk", "Długa", "44/45", "3");
//        Address address4 = new Address("Kościerzyna", "80-004", "Kościerzyna", "Nowa", "5A", "");
//        List<Employee> testEmployees = new ArrayList<>();
//
//        testEmployees.add(new EmployeeBuilder().setFirstName("Adam").setLastName("Superclass").setContact(new Contact("111222333", "adam@company.com")).setAddress(address1).setDepartment(Department.DEPARTAMENT_FINANSOW).setHolidays(22.5f).createEmployee());
//        testEmployees.add(new EmployeeBuilder().setFirstName("Jan").setLastName("Maven").setContact(new Contact("222333444", "jan@company.com")).setAddress(address2).setDepartment(Department.DEPARTAMENT_AKTUARIALNY).setHolidays(44f).createEmployee());
//        testEmployees.add(new EmployeeBuilder().setFirstName("Monika").setLastName("Git").setContact(new Contact("444555666", "monika@company.com")).setAddress(address3).setDepartment(Department.DEPARTAMENT_SPRZEDAZY).setHolidays(0f).createEmployee());
//        testEmployees.add(new EmployeeBuilder().setFirstName("Karolina").setLastName("Pullrequest").setContact(new Contact("999888777", "karolina@company.com")).setAddress(address4).setDepartment(Department.DEPARTAMENT_AKTUARIALNY).setHolidays(0.5f).createEmployee());
//
//        return testEmployees;
//    }
//
//    @Test
//    void shouldFindEmployeeByLastNameReturnOptional() {
//
//        Optional<Employee> result = sut.findEmployeeByLastName("testName");
//
//        assertThat(result).isNotNull();
//        assertThat(result).isInstanceOf(Optional.class);
//    }
//
//    @Test
//    void shouldFindAllEmployeesReturnList() {
//
//        List<Employee> result = sut.findAllEmployees();
//
//        assertThat(result).isInstanceOf(List.class);
//
//    }
//
//    @Test
//    void shouldNotFindByNonExistingLastName() {
//
//        Optional<Employee> result = sut.findEmployeeByLastName("TestLastName");
//
//        assertThat(result).isEmpty();
//    }
//
//
//    @Test
//    void shouldAddedEmployeeNotBeNull() {
//
//        Employee testEmployee = getTestEmployee();
//
//        Employee result = sut.addEmployee(testEmployee);
//
//        assertThat(result).isNotNull();
//    }
//
//    private Employee getTestEmployee() {
//        return new Employee();
//    }
//
//    @Test
//    void shouldFindByLastNameAlwaysReturnOptional() {
//
//        Optional<Employee> employee = sut.findEmployeeByLastName("Maven");
//
//        assertThat(employee).isInstanceOf(Optional.class);
//    }
//
//    @Test
//    void shouldDeleteEmployeeWithGivenLastName() {
//
//        Employee testEmployee = getTestEmployee();
//        Employee addedTestEmployee = sut.addEmployee(testEmployee);
//        String lastName = addedTestEmployee.getLastName();
//
//        sut.deleteEmployeeByLastName(lastName);
//        Optional<Employee> result = sut.findEmployeeByLastName(lastName);
//
//        assertThat(result).isEmpty();
//    }
//
//}