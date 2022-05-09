package com.isa.unasdziala.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class AddHolidaysRequest {
    @NotEmpty
    Set<LocalDate> dates;
}
