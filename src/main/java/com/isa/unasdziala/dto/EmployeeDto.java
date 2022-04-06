package com.isa.unasdziala.dto;

import com.isa.unasdziala.domain.Address;
import com.isa.unasdziala.domain.Contact;
import com.isa.unasdziala.domain.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

public record EmployeeDto(UUID id, String firstName, String lastName, Contact contact, Address address,
                          Department department, float holidays) {

    public EmployeeDto(UUID id, String firstName, String lastName, Contact contact, Address address,
                       Department department, float holidays){
        this(id, firstName, lastName, contact, address, department, holidays);
    }

}
