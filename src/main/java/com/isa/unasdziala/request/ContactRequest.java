package com.isa.unasdziala.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class ContactRequest implements Serializable {
    @Pattern(regexp = "^[\\d]{7,12}$", message = "Number should be between 7 and 12 numbers")
    private String phoneNo;
    @Email
    private String emailAddress;
}
