package com.isa.unasdziala.dto;

import com.isa.unasdziala.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HolidayDto implements Serializable {
    private Long id;
    private LocalDate date;
    private Set<Employee> employees;

    public HolidayDto(LocalDate date) {
        this.date = date;
        this.employees = Set.of();
    }
}
