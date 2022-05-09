package com.isa.unasdziala.controller;


import com.isa.unasdziala.request.DeleteHolidaysRequest;
import com.isa.unasdziala.service.HolidayService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HolidayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HolidayService holidayService;

    @Test
    void shouldGetAllUserHolidaysReturnOkStatus() throws Exception {
        // given
        when(holidayService.findAll(1L)).thenReturn(List.of());
        // when
        // then
        mockMvc.perform(get("/api/employee/{employeeId}/holiday", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteReturnNoContent() throws Exception {
        // given
        // when
        String body = """
                {
                    "holidaysId": [1, 2, 3, 4, 5]
                }
                """;
        mockMvc.perform(
                delete("/api/employee/{employeeId}/holiday", 1L)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}