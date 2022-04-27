package com.isa.unasdziala.controller;

import com.isa.unasdziala.model.Employee;
import com.isa.unasdziala.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll(){
        return employeeService.findAll();
    }

    @GetMapping
    public Employee getById(@PathVariable Long id){
        return employeeService.findById(id);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @PutMapping
    public Employee updateEmployee(@PathVariable Long id, @RequestBody @Valid EmployeeRequest employeeRequest ){
        return employeeService.updateEmployee(id, employeeRequest);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteById(id);
    }
}
