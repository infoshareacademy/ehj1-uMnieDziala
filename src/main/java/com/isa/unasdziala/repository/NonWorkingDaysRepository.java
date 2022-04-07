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
        this.nonWorkingDays = importNonWorkingDays();
    }

    public Day add(Day day) {
        day.setId(UUID.randomUUID());
        nonWorkingDays.add(day);
        return day;
    }

    public Optional<Day> findById(UUID id) {
        return nonWorkingDays.stream().filter(day -> day.getId().equals(id)).findFirst();
    }

    public List<Day> findDaysByLocalDate(LocalDate date) {
        return nonWorkingDays.stream().filter(day -> day.getDate().equals(date)).collect(Collectors.toList());
    }

    public List<Day> findAll() {
        return List.copyOf(nonWorkingDays);
    }

    public Optional<Day> deleteById(UUID id) {
        Optional<Day> day = findById(id);
        day.ifPresent(nonWorkingDays::remove);
        return day;
    }

    public Optional<Day> updateById(UUID id, Day newDay) {
        Optional<Day> optionalDay = findById(id);
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
