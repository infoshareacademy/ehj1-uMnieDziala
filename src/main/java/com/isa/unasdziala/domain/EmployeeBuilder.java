package com.isa.unasdziala.domain;

import com.isa.unasdziala.domain.entities.Holiday;

import java.util.HashSet;
import java.util.Set;

public class EmployeeBuilder {
    private String firstName;
    private String lastName;
    private Contact contact;
    private Address address;
    private Department department;
    private float holidays;
    private Set<Day> events = new HashSet<>();
    private Set<Holiday> holidayDays = new HashSet<>();
    ;

    public EmployeeBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public EmployeeBuilder setContact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public EmployeeBuilder setAddress(Address address) {
        this.address = address;
        return this;
    }

    public EmployeeBuilder setDepartment(Department department) {
        this.department = department;
        return this;
    }

    public EmployeeBuilder setHolidays(float holidays) {
        this.holidays = holidays;
        return this;
    }

    public EmployeeBuilder setEvents(Set<Day> events) {
        this.events = events;
        return this;
    }

    public EmployeeCSV createEmployee() {
        return new EmployeeCSV(firstName, lastName, contact, address, department, holidays);
    }
}