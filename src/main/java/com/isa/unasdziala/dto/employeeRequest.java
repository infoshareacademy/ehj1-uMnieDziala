package com.isa.unasdziala.dto;

import com.isa.unasdziala.model.Address;
import com.isa.unasdziala.model.Contact;
import com.isa.unasdziala.model.Department;
import javax.validation.constraints.Size;

public class employeeRequest {
    @Size(min = 2, max = 30)
    private String firstName;
    @Size(min = 2, max = 30)
    private String lastName;
    private Contact contact;
    private Address address;
    private Department department;
    private float holidays;

    public employeeRequest(String firstName, String lastName, Contact contact, Address address, Department department, float holidays) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        this.address = address;
        this.department = department;
        this.holidays = holidays;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setHolidays(float holidays) {
        this.holidays = holidays;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public Department getDepartment() {
        return department;
    }

    public float getHolidays() {
        return holidays;
    }
}

