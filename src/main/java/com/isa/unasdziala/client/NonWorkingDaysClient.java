package com.isa.unasdziala.client;

import com.isa.unasdziala.configuration.NonWorkingDaysConfiguration;
import com.isa.unasdziala.dto.HolidayDto;
import com.isa.unasdziala.dto.NonWorkingDayApiResponse;
import com.isa.unasdziala.model.NonWorkingDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class NonWorkingDaysClient {
    private final RestTemplate restTemplate;
    private final NonWorkingDaysConfiguration configuration;

    @Autowired
    public NonWorkingDaysClient(RestTemplate restTemplate, NonWorkingDaysConfiguration configuration) {
        this.restTemplate = restTemplate;
        this.configuration = configuration;
    }

    @Cacheable(value = "NonWorkingDays")
    public List<NonWorkingDay> getAll() {
        String url = configuration.getBaseUrl() +
                "country=" + configuration.getCountry() +
                "&year=" + LocalDate.now().getYear() +
                "&api_key=" + configuration.getApiKey() +
                "&type=" + configuration.getType();


        System.out.println("Cached: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy")));
        return Objects.requireNonNull(restTemplate.getForObject(url, NonWorkingDayApiResponse.class)).getNonWorkingDays();
    }

    public List<HolidayDto> getAllAsDayDtoList() {
        return getAll().stream().map(day -> new HolidayDto(day.getDate())).collect(Collectors.toList());
    }
}
