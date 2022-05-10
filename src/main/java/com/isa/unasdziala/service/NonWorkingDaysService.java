package com.isa.unasdziala.service;

import com.isa.unasdziala.dto.NonWorkingDayDto;
import com.isa.unasdziala.repository.NonWorkingDaysRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NonWorkingDaysService {
    private final NonWorkingDaysRepository nonWorkingDaysRepository;
    private final ModelMapper mapper;

    public NonWorkingDaysService(NonWorkingDaysRepository nonWorkingDaysRepository, ModelMapper mapper) {
        this.nonWorkingDaysRepository = nonWorkingDaysRepository;
        this.mapper = mapper;
    }

    public List<NonWorkingDayDto> getAll() {
        return nonWorkingDaysRepository.getAll().stream()
                .map(day -> mapper.map(day, NonWorkingDayDto.class))
                .collect(Collectors.toList());
    }
}
