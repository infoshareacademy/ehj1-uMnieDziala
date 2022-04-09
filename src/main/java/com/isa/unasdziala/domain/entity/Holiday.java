package com.isa.unasdziala.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "holidays")
@Getter
@Setter
@NoArgsConstructor
@ToString
@NamedQueries({
        @NamedQuery(name = "Holiday.findByDate", query = "from Holiday h where h.date =:date"),
})
public class Holiday {
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid2")
    @Type(type = "uuid-char")
    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;

    @Column
    private LocalDate date;

    @ManyToMany(mappedBy = "holidays", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Employee> employees = new HashSet<>();

    public Holiday(LocalDate date) {
        this.date = date;
    }
}
