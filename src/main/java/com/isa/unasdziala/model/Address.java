package com.isa.unasdziala.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {
    private String city;
    private String zipCode;
    private String postCity;
    private String street;
    private String houseNumber;
    private String flatNumber;

    @Override
    public String toString() {
        return "Address: " + city + " " + zipCode + " " + street + " " + houseNumber + "/" + flatNumber;
    }
}