package com.isa.unasdziala.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Department {

    ACTUARIAL_DEPARTMENT("Actuarial Department"),
    SALES_DEPARTMENT("Sales Department"),
    FINANCE_DEPARTMENT("Finance Department");

    public String description;
    public static final List<Department> VALUES = List.of(Department.values());
    public static final Map<String, Department> DESCRIPTION_TO_DEPARTMENT_MAP = new HashMap<>();

    static {
        for (Department category : VALUES) {
            DESCRIPTION_TO_DEPARTMENT_MAP.put(category.description, category);
        }
    }

    Department(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Department fromString(String category) {
        return DESCRIPTION_TO_DEPARTMENT_MAP.get(category);
    }
}