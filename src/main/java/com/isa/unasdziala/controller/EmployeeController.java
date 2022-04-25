package com.isa.unasdziala.controller;

import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findById(@PathVariable("id") UUID id) {
        throw new RuntimeException();
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        throw new RuntimeException();
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") UUID id, @RequestBody EmployeeDto employeeDto) {
        throw new RuntimeException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") UUID id) {
        throw new RuntimeException();
    }
}
