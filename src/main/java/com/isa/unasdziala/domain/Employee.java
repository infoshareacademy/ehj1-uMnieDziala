package com.isa.unasdziala.domain;

import com.isa.unasdziala.domain.converter.DepartmentConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvRecurse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "employees")

public class Employee {

    @CsvBindByPosition(position = 0)
    private String firstName;
    @CsvBindByPosition(position = 1)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    @CsvRecurse
    private Contact contact;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_address_id")
    @CsvRecurse
    private Address address;

    @CsvCustomBindByPosition(position = 10, converter = DepartmentConverter.class)
    private Department department;
    @CsvBindByPosition(position = 11)
    private float holidays;

    public Employee(String firstName, String lastName, Contact contact, Address address, Department department, float holidays) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
        this.department = department;
        this.holidays = holidays;
    }
}
