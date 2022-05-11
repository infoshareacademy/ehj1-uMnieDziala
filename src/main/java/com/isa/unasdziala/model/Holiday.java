package com.isa.unasdziala.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Holiday")
@Table(name = "holidays")
@Getter
@Setter
@NoArgsConstructor
public class Holiday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;


    @ManyToMany(mappedBy = "holidayDays", cascade = CascadeType.MERGE)
    private Set<Employee> employees = new HashSet<>();

    public Holiday(LocalDate date) {
        this.date = date;
    }
}