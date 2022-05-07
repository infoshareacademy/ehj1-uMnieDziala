package com.isa.unasdziala.request;

import com.isa.unasdziala.model.Department;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeRequest {
    @Size(min = 2, max = 30)
    private String firstName;
    @Size(min = 2, max = 30)
    private String lastName;
    @NotNull
    private ContactRequest contact;
    @NotNull
    private AddressRequest address;
    @NotNull
    private Department department;
    @Min(value = 0)
    @Max(value = 100)
    private float holidays;

    public EmployeeRequest(String firstName, String lastName, ContactRequest contact, AddressRequest address, Department department, float holidays) {
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

    public void setContact(ContactRequest contact) {
        this.contact = contact;
    }

    public void setAddress(AddressRequest address) {
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

    public ContactRequest getContact() {
        return contact;
    }

    public AddressRequest getAddress() {
        return address;
    }

    public Department getDepartment() {
        return department;
    }

    public float getHolidays() {
        return holidays;
    }
}

