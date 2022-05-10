package com.isa.unasdziala.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NonWorkingDayDto {
    private String name;
    private String description;
    private LocalDate date;
}
