package com.isa.unasdziala.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Embedded
    @Column(nullable = true)
    private Contact contact;

    @Embedded
    @Column(nullable = true)
    private Address address;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(nullable = true)
    private float holidays;

//    @Transient
//    private Set<Day> events;

    @ManyToMany(mappedBy = "employees", cascade = CascadeType.MERGE)
    private Set<Holiday> holidayDays = new HashSet<>();

    public Employee() {
    }
}
