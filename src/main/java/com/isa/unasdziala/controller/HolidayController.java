package com.isa.unasdziala.controller;

import com.isa.unasdziala.dto.HolidayDto;
import com.isa.unasdziala.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/employee/{userId}/holiday")
public class HolidayController {

    private final HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public ResponseEntity<List<HolidayDto>> getAll(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(holidayService.findAll(userId));
    }

    @PostMapping
    public ResponseEntity<HolidayDto> addHoliday(@PathVariable("userId") Long userId, @RequestBody Collection<LocalDate> dates) {
        return ResponseEntity.ok(holidayService.addHoliday(userId, dates));
    }
}
