package com.isa.unasdziala.repository;

import com.isa.unasdziala.dto.HolidayDto;
import com.isa.unasdziala.dto.NonWorkingDayApiResponse;
import com.isa.unasdziala.model.NonWorkingDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class NonWorkingDaysRepository {
    private final RestTemplate restTemplate;
    private List<NonWorkingDay> nonWorkingDayList;

    @Autowired
    public NonWorkingDaysRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<NonWorkingDay> getAll() {
        return List.copyOf(nonWorkingDayList);
    }

    public List<HolidayDto> getAllAsDayDtoList() {
        return nonWorkingDayList.stream().map(day -> new HolidayDto(day.getDate())).collect(Collectors.toList());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        var year = LocalDate.now().getYear();
        String URL = "https://calendarific.com/api/v2/holidays?country=pl&year=" +
                year +
                "&api_key=5b7d1f3039c481c46d91c60dfbe7310b6e24a5c7&type=national";

        nonWorkingDayList = Objects.requireNonNull(restTemplate.getForObject(URL, NonWorkingDayApiResponse.class)).getNonWorkingDays();

    }

}
