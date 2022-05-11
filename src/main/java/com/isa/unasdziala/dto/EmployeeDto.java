package com.isa.unasdziala.dto;

import com.isa.unasdziala.model.Address;
import com.isa.unasdziala.model.Contact;
import com.isa.unasdziala.model.Department;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private Contact contact;
    private Department department;
    private float holidays;
}
