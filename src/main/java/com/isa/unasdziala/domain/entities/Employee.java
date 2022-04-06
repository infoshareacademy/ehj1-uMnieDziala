package com.isa.unasdziala.domain.entities;

import com.isa.unasdziala.domain.Address;
import com.isa.unasdziala.domain.Contact;
import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.domain.Department;
import com.isa.unasdziala.domain.converter.DepartmentConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvRecurse;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Builder
@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
public class Employee { // rozdzielić

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid2")
    @Type(type = "uuid-char")
    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;

    @CsvBindByPosition(position = 0)
    @Column(nullable = false)
    private String firstName;

    @CsvBindByPosition(position = 1)
    @Column(nullable = false)
    private String lastName;

    @CsvRecurse
    @Embedded
    @Column(nullable = false)
    private Contact contact;

    @CsvRecurse
    @Embedded
    @Column(nullable = false)
    private Address address;

    @CsvCustomBindByPosition(position = 10, converter = DepartmentConverter.class)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Department department;

    @CsvBindByPosition(position = 11)
    @Column(nullable = false)
    private float holidays;
//    private Set<Day> events;  // LocalDate
}