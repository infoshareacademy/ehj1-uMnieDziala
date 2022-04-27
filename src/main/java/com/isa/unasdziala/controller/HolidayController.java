package com.isa.unasdziala.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

@Controller
@RequestMapping("/api/employee/{userId}/holiday")
public class HolidayController {

    private HolidayService holidayService;

    @GetMapping
    public ResponseEntity<List<HolidayDto>> getAll(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(holidayService.findAll(userId));
    }

    @PostMapping
    public ResponseEntity<HolidayDto> addHoliday(@PathVariable("userId") Long userId, @RequestBody Collection<LocalDate> dates) {
        return ResponseEntity.ok(holidayService.addHoliday(userId, dates));
    }
}
