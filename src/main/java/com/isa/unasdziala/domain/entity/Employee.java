package com.isa.unasdziala.domain.entity;

import com.isa.unasdziala.domain.Address;
import com.isa.unasdziala.domain.Contact;
import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.domain.Department;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@NamedQueries({@NamedQuery(name = "Employee.findAll", query = "from Employee"), @NamedQuery(name = "Employee.findByFirstNameAndLastName", query = "from Employee e where e.firstName = :firstName and e.lastName = :lastName"),})
public class Employee {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid2")
    @Type(type = "uuid-char")
    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Embedded
    @Column(nullable = false)
    private Contact contact;

    @Embedded
    @Column(nullable = false)
    private Address address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Department department;

    @Column(nullable = false)
    private float holidays;

    @Transient
    private Set<Day> events;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Holiday> holidayDays = new HashSet<>();

    public Employee() {
    }
}