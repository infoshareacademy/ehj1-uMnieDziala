package com.isa.unasdziala.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Embedded
    @Column()
    private Contact contact;

    @Embedded
    @Column()
    private Address address;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Column()
    private float holidays;
    
    @ManyToMany(mappedBy = "employees", cascade = CascadeType.MERGE)
    private Set<Holiday> holidayDays = new HashSet<>();

    public Employee() {
    }
}
