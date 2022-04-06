package com.isa.unasdziala.domain;

import com.isa.unasdziala.domain.converter.DepartmentConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvRecurse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @CsvBindByPosition(position = 0)
    private String firstName;
    @CsvBindByPosition(position = 1)
    private String lastName;
    @CsvRecurse
    private Contact contact;
    @CsvRecurse
    private Address address;
    @CsvCustomBindByPosition(position = 10, converter = DepartmentConverter.class)
    private Department department;
    @CsvBindByPosition(position = 11)
    private float holidays;
    private Set<Day> events=new HashSet<>();


    public Employee(String firstName, String lastName, Contact contact, Address address, Department department, float holidays) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
        this.department = department;
        this.holidays = holidays;
        this.events = new HashSet<Day>();
    }
}
