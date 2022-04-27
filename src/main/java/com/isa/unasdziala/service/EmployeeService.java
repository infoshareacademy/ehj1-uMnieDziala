package com.isa.unasdziala.service;

import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.exception.ResourceNotFoundException;
import com.isa.unasdziala.model.Employee;
import com.isa.unasdziala.repository.EmployeeRepository;
import com.isa.unasdziala.request.EmployeeRequest;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    public List<EmployeeDto> findAll(){
        return employeeRepository.findAll()
                .stream().map(employee -> mapper.map(employee, EmployeeDto.class))
                .toList();

    }

    public EmployeeDto findById(Long id){
        return employeeRepository.findById(id)
                .map(employee -> mapper.map(employee,EmployeeDto.class))
                .orElseThrow(() -> new ResourceNotFoundException(format("Employee with id %d not found.", id)));
    }


    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeRequest employeeRequest) {
//        var employee = getById(id);
        return null;
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
