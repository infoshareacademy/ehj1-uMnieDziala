package com.isa.unasdziala.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class AddHolidaysRequest {
    Set<LocalDate> dates;
}
