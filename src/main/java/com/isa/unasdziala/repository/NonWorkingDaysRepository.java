package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.services.properties.AppProperties;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class NonWorkingDaysRepository {
    private final List<Day> nonWorkingDays = importNonWorkingDays();

    private List<Day> importNonWorkingDays() {
        NonWorkingDaysReader nonWorkingDaysReader = new NonWorkingDaysReader();
        String countryName = new AppProperties().getCountryName();
        return nonWorkingDaysReader.getNonWorkingDays().stream()
                .filter(day -> day.getCountry().equals(countryName))
                .collect(Collectors.toList());
    }
}
