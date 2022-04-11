package com.isa.unasdziala.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
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

    @Override
    public String toString() {
        return "Address: " + city + " " + zipCode + " " + street + " " + houseNumber + "/" + flatNumber;
    }
}
