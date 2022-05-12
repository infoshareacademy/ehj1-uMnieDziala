package com.isa.unasdziala.client;

import com.isa.unasdziala.dto.HolidayDto;
import com.isa.unasdziala.dto.NonWorkingDayApiResponse;
import com.isa.unasdziala.model.NonWorkingDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class NonWorkingDaysClient {
    private final RestTemplate restTemplate;

    @Autowired
    public NonWorkingDaysClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(value = "NonWorkingDays")
    public List<NonWorkingDay> getAll() {
        var year = LocalDate.now().getYear();
        String URL = "https://calendarific.com/api/v2/holidays?country=pl&year=" +
                year +
                "&api_key=5b7d1f3039c481c46d91c60dfbe7310b6e24a5c7&type=national";
        System.out.println("Działa: " + LocalDateTime.now());
        return Objects.requireNonNull(restTemplate.getForObject(URL, NonWorkingDayApiResponse.class)).getNonWorkingDays();
    }

    public List<HolidayDto> getAllAsDayDtoList() {
        return getAll().stream().map(day -> new HolidayDto(day.getDate())).collect(Collectors.toList());
    }
}
