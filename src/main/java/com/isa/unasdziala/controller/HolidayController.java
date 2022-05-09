package com.isa.unasdziala.controller;

import com.isa.unasdziala.dto.HolidayDto;
import com.isa.unasdziala.request.AddHolidaysRequest;
import com.isa.unasdziala.request.DeleteHolidaysRequest;
import com.isa.unasdziala.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee/{employeeId}/holiday")
public class HolidayController {

    private final HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public ResponseEntity<List<HolidayDto>> getAll(@PathVariable("employeeId") Long userId) {
        return ResponseEntity.ok(holidayService.findAll(userId));
    }

    @GetMapping("/{holidayId}")
    public ResponseEntity<HolidayDto> getHolidayById(@PathVariable("employeeId") Long userId, @PathVariable("holidayId") Long holidayId) {
        return ResponseEntity.ok(holidayService.findHolidayById(userId, holidayId));
    }

    @PostMapping("")
    public ResponseEntity<List<HolidayDto>> addHoliday(@PathVariable("employeeId") Long employeeId, @RequestBody AddHolidaysRequest addHolidaysRequest) {
        return ResponseEntity.ok(holidayService.addHoliday(employeeId, addHolidaysRequest));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteById(@PathVariable("employeeId") Long userId,
                                        @RequestBody DeleteHolidaysRequest deleteHolidaysRequest) {
        holidayService.deleteById(userId, deleteHolidaysRequest);
        return ResponseEntity.noContent().build();
    }
}
