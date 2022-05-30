package com.isa.unasdziala.controller;

import com.isa.unasdziala.dto.NonWorkingDayDto;
import com.isa.unasdziala.service.NonWorkingDaysService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nonworkingdays")
@CrossOrigin(origins = "*")
public class NonWorkingDayController {
    private final NonWorkingDaysService service;

    public NonWorkingDayController(NonWorkingDaysService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<NonWorkingDayDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}
