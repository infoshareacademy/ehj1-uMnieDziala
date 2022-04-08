package com.isa.unasdziala.dto;

import com.isa.unasdziala.domain.Address;
import com.isa.unasdziala.domain.Contact;
import com.isa.unasdziala.domain.Department;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class EmployeeDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private Contact contact;
    private Address address;
    private Department department;
    private float holidays;
}
