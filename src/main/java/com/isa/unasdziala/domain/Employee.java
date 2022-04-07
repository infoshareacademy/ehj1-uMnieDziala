package com.isa.unasdziala.domain;

import com.isa.unasdziala.domain.converter.DepartmentConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvIgnore;
import com.opencsv.bean.CsvRecurse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid2")
    @Type(type = "uuid-char")
    @Column
    private UUID id;
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
    @CsvIgnore
    private Set<Day> events = new HashSet<>();
    @CsvIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "employee_holiday",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "holiday_id")
    )
    private Set<Holiday> holidayDays;


    public Employee(String firstName, String lastName, Contact contact, Address address, Department department, float holidays) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
        this.department = department;
        this.holidays = holidays;
        this.events = new HashSet<>();
        this.holidayDays = new HashSet<>();

    }
}
