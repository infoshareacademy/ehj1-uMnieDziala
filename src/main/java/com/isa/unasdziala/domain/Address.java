package com.isa.unasdziala.domain;
import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address {
    @CsvBindByPosition(position = 4)
    String city;
    @CsvBindByPosition(position = 5)
    String zipCode;
    @CsvBindByPosition(position = 6)
    String postCity;
    @CsvBindByPosition(position = 7)
    String street;
    @CsvBindByPosition(position = 8)
    String houseNumber;
    @CsvBindByPosition(position = 9)
    String  flatNumber;
    @OneToOne(mappedBy = "address")
    Employee employee;
    }
