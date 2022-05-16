package com.isa.unasdziala.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Contact {

    private String phoneNo;
    private String emailAddress;

    @Override
    public String toString() {
        return "PhoneNo: " + phoneNo + "\t mail: " + emailAddress;
    }
}
