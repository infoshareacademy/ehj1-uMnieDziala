package com.isa.unasdziala.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class HolidayDto {
    private Long id;
    private LocalDate date;

    public HolidayDto(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }
}
