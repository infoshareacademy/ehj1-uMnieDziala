package com.isa.unasdziala.domain.entities;
import com.isa.unasdziala.domain.Address;
import com.isa.unasdziala.domain.Contact;
import com.isa.unasdziala.domain.Department;
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

    public Employee(UUID id, String firstName, String lastName, Contact contact, Address address, Department department, float holidays) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
        this.department = department;
        this.holidays = holidays;
    }

    public Employee() {

    }
}