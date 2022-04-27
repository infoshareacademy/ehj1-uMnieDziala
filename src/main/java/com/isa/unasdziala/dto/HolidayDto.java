package com.isa.unasdziala.dto;

import com.isa.unasdziala.model.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class HolidayDto {
    private Long id;
    private LocalDate date;
    private Set<Employee> employees;

    public HolidayDto(Long id, LocalDate date, Set<Employee> employees) {
        this.id = id;
        this.date = date;
        this.employees = employees;
    }
}
