package com.isa.unasdziala.request;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class AddressRequest implements Serializable {
    @Size(min = 3, max = 40)
    private String city;
    @Pattern(regexp = "^\\d{2}-?\\d{3}$")
    private String zipCode;
    @Size(min = 3, max = 40)
    private String postCity;
    @Size(min = 3, max = 40)
    private String street;
    @Size(min = 1, max = 5)
    private String houseNumber;
    @Size(min = 1, max = 5)
    private String flatNumber;
}
