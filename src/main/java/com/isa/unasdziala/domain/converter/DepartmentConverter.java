package com.isa.unasdziala.domain.converter;

import com.isa.unasdziala.domain.Department;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class DepartmentConverter extends AbstractBeanField {

        @Override
        protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
            return Department.fromString(s);
        }
    }
