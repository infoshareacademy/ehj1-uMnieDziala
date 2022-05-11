package com.isa.unasdziala.controller;

import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.exception.ResourceNotFoundException;
import com.isa.unasdziala.model.Address;
import com.isa.unasdziala.model.Contact;
import com.isa.unasdziala.model.Department;
import com.isa.unasdziala.request.EmployeeRequest;
import com.isa.unasdziala.service.EmployeeService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;


    @Test
    void shouldReturnAllEmployees() throws Exception {
        List<EmployeeDto> testEmployees = new ArrayList<>();
        testEmployees.add(new EmployeeDto());

        Mockito.when(employeeService.findAll()).thenReturn(testEmployees);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)))
                .andExpect(status().isOk());

    }

    @Test
    void shouldFindEmployeeById() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();

        Mockito.when(employeeService.findById(1L)).thenReturn(employeeDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturn404ExceptionWhenEmployeeNotFound() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();

        Mockito.when(employeeService.findById(1L)).thenThrow(new ResourceNotFoundException("Not found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    void shouldProperlyAddEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("Jan");
        employeeDto.setLastName("Kowalski");
        employeeDto.setDepartment(Department.ACTUARIAL_DEPARTMENT);
        employeeDto.setContact(new Contact("123456789", "test@test.pl"));
        employeeDto.setAddress(new Address("TestCity", "00-000", "TestPostCity",
                "TestStreet", "0", "0"));
        employeeDto.setHolidays(9.0f);

        Mockito.when(employeeService.addEmployee(any(EmployeeRequest.class))).thenReturn(employeeDto);

        String requestBody = """
                   {
                       "id": 1,
                       "firstName": "Jan",
                       "lastName": "Kowalski",
                       "contact": {
                           "phoneNo": "123456789",
                           "emailAddress": "test@test.pl"
                       },
                       "address": {
                           "city": "TestCity",
                           "zipCode": "00-000",
                           "postCity": "TestPostCity",
                           "street": "TestStreet",
                           "houseNumber": "0",
                           "flatNumber": "0"
                       },
                       "department": "ACTUARIAL_DEPARTMENT",
                       "holidays": 9.0
                   }
                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/api/employee/").content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("Jan")))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo("Kowalski")))
                .andExpect(jsonPath("$.contact.phoneNo", Matchers.equalTo("123456789")))
                .andExpect(jsonPath("$.contact.emailAddress", Matchers.equalTo("test@test.pl")))
                .andExpect(jsonPath("$.address.city", Matchers.equalTo("TestCity")))
                .andExpect(jsonPath("$.address.zipCode", Matchers.equalTo("00-000")))
                .andExpect(jsonPath("$.address.postCity", Matchers.equalTo("TestPostCity")))
                .andExpect(jsonPath("$.address.street", Matchers.equalTo("TestStreet")))
                .andExpect(jsonPath("$.address.houseNumber", Matchers.equalTo("0")))
                .andExpect(jsonPath("$.address.flatNumber", Matchers.equalTo("0")))
                .andExpect(jsonPath("$.holidays", Matchers.equalTo(9.0)));
    }

    @Test
    void shouldCorrectlyDeleteEmployee() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/{id}", 1L))
                .andExpect(status().isNoContent());

    }

    @Test
    void shouldProperlyUpdateEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setFirstName("Jan");
        employeeDto.setLastName("Kowalski");
        employeeDto.setDepartment(Department.ACTUARIAL_DEPARTMENT);
        employeeDto.setContact(new Contact("123456789", "test@test.pl"));
        employeeDto.setAddress(new Address("TestCity", "00-000", "TestPostCity",
                "TestStreet", "0", "0"));
        employeeDto.setHolidays(9.0f);

        Mockito.when(employeeService.updateEmployee(eq(1L), any(EmployeeRequest.class))).thenReturn(employeeDto);

        String requestBody = """
                   {
                       "id": 1,
                       "firstName": "Jan",
                       "lastName": "Kowalski",
                       "contact": {
                           "phoneNo": "123456789",
                           "emailAddress": "test@test.pl"
                       },
                       "address": {
                           "city": "TestCity",
                           "zipCode": "00-000",
                           "postCity": "TestPostCity",
                           "street": "TestStreet",
                           "houseNumber": "0",
                           "flatNumber": "0"
                       },
                       "department": "ACTUARIAL_DEPARTMENT",
                       "holidays": 9.0
                   }
                """;
        mockMvc.perform(MockMvcRequestBuilders.put("/api/employee/{id}", 1L).content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.firstName", Matchers.equalTo("Jan")))
                .andExpect(jsonPath("$.lastName", Matchers.equalTo("Kowalski")))
                .andExpect(jsonPath("$.contact.phoneNo", Matchers.equalTo("123456789")))
                .andExpect(jsonPath("$.contact.emailAddress", Matchers.equalTo("test@test.pl")))
                .andExpect(jsonPath("$.address.city", Matchers.equalTo("TestCity")))
                .andExpect(jsonPath("$.address.zipCode", Matchers.equalTo("00-000")))
                .andExpect(jsonPath("$.address.postCity", Matchers.equalTo("TestPostCity")))
                .andExpect(jsonPath("$.address.street", Matchers.equalTo("TestStreet")))
                .andExpect(jsonPath("$.address.houseNumber", Matchers.equalTo("0")))
                .andExpect(jsonPath("$.address.flatNumber", Matchers.equalTo("0")))
                .andExpect(jsonPath("$.holidays", Matchers.equalTo(9.0)));
    }
}
