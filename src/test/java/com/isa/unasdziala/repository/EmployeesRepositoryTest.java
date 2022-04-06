package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.*;
import com.isa.unasdziala.domain.entities.Employee;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

class EmployeesRepositoryTest {

    private EmployeesRepository sut = new EmployeesRepository();

    @Test
    void shouldContainsOnlyTestEmployeesFromFile() {
        // given
        List<Employee> refferenceEmployees = getRefferenceEmployees();
        // when
        List<Employee> result = sut.importEmployees();
        // then
        assertThat(refferenceEmployees).containsExactlyElementsOf(result);
    }


    private List<Employee> getRefferenceEmployees() {

        Address address1 = new Address("Bylowo-Leśnictwo", "80-002", "Kartuzy", "", "5", "");
        Address address2 = new Address("Gdynia", "80-001", "Gdynia", "Starowiejska", "5", "33");
        Address address3 = new Address("Gdańsk", "80-003", "Gdańsk", "Długa", "44/45", "3");
        Address address4 = new Address("Kościerzyna", "80-004", "Kościerzyna", "Nowa", "5A", "");
        List<Employee> testEmployees = new ArrayList<>();

        testEmployees.add(new Employee(UUID.randomUUID(), "Adam", "Superclass", new Contact("111222333", "adam@company.com"), address1, Department.DEPARTAMENT_FINANSOW, 22.5f));
        testEmployees.add(new Employee(UUID.randomUUID(), "Jan", "Maven", new Contact("222333444", "jan@company.com"), address2, Department.DEPARTAMENT_AKTUARIALNY, 44f));
        testEmployees.add(new Employee(UUID.randomUUID(), "Monika", "Git", new Contact("444555666", "monika@company.com"), address3, Department.DEPARTAMENT_SPRZEDAZY, 0f));
        testEmployees.add(new Employee(UUID.randomUUID(), "Karolina", "Pullrequest", new Contact("999888777", "karolina@company.com"), address4, Department.DEPARTAMENT_AKTUARIALNY, 0.5f));

        return testEmployees;
    }

    @Test
    void shouldFindEmployeeByLastNameReturnOptional() {

        Optional<Employee> result = sut.findEmployeeByLastName("testName");

        assertThat(result).isNotNull();
        assertThat(result).isInstanceOf(Optional.class);
    }

    @Test
    void shouldFindAllEmployeesReturnList() {

        List<Employee> result = sut.findAllEmployees();

        assertThat(result).isInstanceOf(List.class);

    }



    @Test
    void shouldUpdateEmployeeByLastName() {
        // given

        // when

        // then

    }

    @Test
    void shouldDeleteEmployeeByLastName() {
        // given

        // when

        // then

    }

    @Test
    void shouldAddEmployee() {
        // given

        // when

        // then

    }
}