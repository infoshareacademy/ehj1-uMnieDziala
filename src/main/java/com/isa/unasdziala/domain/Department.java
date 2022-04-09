package com.isa.unasdziala.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Department {

    ACTUARIAL_DEPARTMENT("Actuarial Department"),
    SALES_DEPARTAMENT("Sales Department"),
    FINANCIAL_DEPARTMENT("Financial Departament");

    private String description;
    public static final List<Department> VALUES = List.of(Department.values());
    private static final Map<String, Department> DESCRIPTION_TO_DEPARTMENT_MAP = new HashMap<>();
    private static final Map<Department, String> DEPARTMENT_TO_DESCRIPTION_MAP = new HashMap<>();
    static {
        for (Department department : VALUES) {
            DESCRIPTION_TO_DEPARTMENT_MAP.put(department.description, department);
            DEPARTMENT_TO_DESCRIPTION_MAP.put(department, department.description);
        }
    }

    Department(String description) {
        this.description = description;
    }


    public static Department fromString(String category) {
        return DESCRIPTION_TO_DEPARTMENT_MAP.get(category);
    }

    public static String fromDepartment(Department department) {
        return DEPARTMENT_TO_DESCRIPTION_MAP.get(department);
    }
}