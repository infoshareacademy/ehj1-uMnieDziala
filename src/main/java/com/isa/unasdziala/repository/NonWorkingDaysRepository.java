package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.services.properties.AppProperties;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter
public class NonWorkingDaysRepository {

    private final static Logger log = LoggerFactory.getLogger(NonWorkingDaysRepository.class);
    private List<Day> nonWorkingDays = new ArrayList<>();

    public NonWorkingDaysRepository() {
    }

    public void initialize() {
        log.info("Initialize non working days repository");
        if (this.nonWorkingDays.isEmpty()) {
            this.nonWorkingDays = importNonWorkingDays();
        }
    }

    public Optional<Day> add(Day day) {
        Optional<Day> optionalDay = findByDate(day.getDate());
        if (optionalDay.isEmpty()) {
            day.setId(UUID.randomUUID());
            nonWorkingDays.add(day);
            return Optional.of(day);
        }
        return Optional.empty();
    }

    public Optional<Day> findByDate(LocalDate date) {
        return nonWorkingDays.stream().filter(day -> day.getDate().equals(date)).findFirst();
    }

    public List<Day> findAll() {
        return List.copyOf(nonWorkingDays);
    }

    public Optional<Day> deleteByDate(LocalDate date) {
        Optional<Day> day = findByDate(date);
        day.ifPresent(nonWorkingDays::remove);
        return day;
    }

    public Optional<Day> updateByDate(Day newDay) {
        Optional<Day> optionalDay = findByDate(newDay.getDate());
        if (optionalDay.isPresent()) {
            Day day = optionalDay.get();
            day.setDate(newDay.getDate());
            day.setName(newDay.getName());
            day.setDescription(newDay.getDescription());
        }
        return optionalDay;
    }

    private List<Day> importNonWorkingDays() {
        log.info("Start import non working days to repository");
        NonWorkingDaysReader nonWorkingDaysReader = new NonWorkingDaysReader();
        Locale countryName = new AppProperties().getCountryName();
        log.info("Filtr non working days country by: {}", countryName.getCountry());
        List<Day> nonWorkingDaysRepository = nonWorkingDaysReader.getNonWorkingDays().stream()
                .filter(day -> day.getCountry().equals(countryName.getCountry().toLowerCase()))
                .collect(Collectors.toList());
        log.info("Have been imported {} day/s to country: {}", nonWorkingDaysRepository.size(), countryName.getCountry());
        return nonWorkingDaysRepository;
    }
}
