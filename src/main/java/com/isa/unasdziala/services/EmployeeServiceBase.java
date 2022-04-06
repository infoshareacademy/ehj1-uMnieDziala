package com.isa.unasdziala.services;

import com.isa.unasdziala.dao.EmployeeDao;
import com.isa.unasdziala.dto.EmployeeDto;

import java.util.List;

public class EmployeeServiceBase {

    private final EmployeeDao employeeDao;

    public EmployeeServiceBase(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<EmployeeDto> findAll() {
        return employeeDao.findAll();
    }
}
