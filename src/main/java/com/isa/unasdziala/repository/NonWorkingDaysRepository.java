package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.services.properties.AppProperties;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Getter
public class NonWorkingDaysRepository {

    private final static Logger LOGGER = LoggerFactory.getLogger(NonWorkingDaysRepository.class);
    private final List<Day> nonWorkingDays = new ArrayList<>();

    public NonWorkingDaysRepository() {
        importNonWorkingDays();
    }

    public Day add(Day day) {
        day.setId(UUID.randomUUID());
        nonWorkingDays.add(day);
        return day;
    }

    public Optional<Day> findById(UUID id) {
        return nonWorkingDays.stream().filter(day -> day.getId().equals(id)).findFirst();
    }

    public Optional<Day> findDayByLocalDate(LocalDate date) {
        return nonWorkingDays.stream().filter(day -> day.getDate().equals(date)).findFirst();
    }

    public List<Day> findAll() {
        return List.copyOf(nonWorkingDays);
    }

    public Optional<Day> deleteByDate(LocalDate date) {
        Optional<Day> day = findDayByLocalDate(date);
        day.ifPresent(nonWorkingDays::remove);
        return day;
    }

    public Optional<Day> updateByDate(Day newDay) {
        Optional<Day> optionalDay = findDayByLocalDate(newDay.getDate());
        if (optionalDay.isPresent()) {
            Day day = optionalDay.get();
            day.setDate(newDay.getDate());
            day.setName(newDay.getName());
            day.setDescription(newDay.getDescription());
        }
        return optionalDay;
    }

    private void importNonWorkingDays() {
        LOGGER.info("Start import non working days to repository");
        NonWorkingDaysReader nonWorkingDaysReader = new NonWorkingDaysReader();
        String countryName = new AppProperties().getCountryName();
        LOGGER.info("Filtr non working days country by: {}", countryName);
        nonWorkingDaysReader.getNonWorkingDays().stream()
                .filter(day -> day.getCountry().equals(countryName))
                .forEach(this::add);
        LOGGER.info("Have been imported {} day/s to country: {}", nonWorkingDays.size(), countryName);

    }
}
