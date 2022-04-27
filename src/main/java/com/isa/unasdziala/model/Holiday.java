package com.isa.unasdziala.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Holiday")
@Table(name = "holidays")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Holiday {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private LocalDate date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "holiday_employe",
            joinColumns = @JoinColumn(name = "holiday_id"),
            inverseJoinColumns = @JoinColumn(name = "employe_id")
    )
    private Set<Employee> employees = new HashSet<>();

    public Holiday(LocalDate date) {
        this.date = date;
    }
}