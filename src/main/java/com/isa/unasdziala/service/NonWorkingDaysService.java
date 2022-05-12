package com.isa.unasdziala.service;

import com.isa.unasdziala.client.NonWorkingDaysClient;
import com.isa.unasdziala.dto.NonWorkingDayDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NonWorkingDaysService {
    private final NonWorkingDaysClient nonWorkingDaysClient;
    private final ModelMapper mapper;

    public NonWorkingDaysService(NonWorkingDaysClient nonWorkingDaysClient, ModelMapper mapper) {
        this.nonWorkingDaysClient = nonWorkingDaysClient;
        this.mapper = mapper;
    }

    public List<NonWorkingDayDto> getAll() {
        return nonWorkingDaysClient.getAll().stream()
                .map(day -> mapper.map(day, NonWorkingDayDto.class))
                .collect(Collectors.toList());
    }
}
