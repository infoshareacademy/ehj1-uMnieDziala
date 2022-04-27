package com.isa.unasdziala.dto;

import com.isa.unasdziala.model.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Department department;
    private float holidays;
}
