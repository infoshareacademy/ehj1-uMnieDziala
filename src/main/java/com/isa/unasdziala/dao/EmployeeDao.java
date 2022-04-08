package com.isa.unasdziala.dao;

import com.isa.unasdziala.domain.entities.Employee;
import com.isa.unasdziala.adapters.EmployeeAdapter;
import com.isa.unasdziala.dto.EmployeeDto;
import com.isa.unasdziala.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class EmployeeDao {

    private final EntityManager em = HibernateUtil.getEntityManager();

    private final EmployeeAdapter adapter = new EmployeeAdapter();

    public EmployeeDto save(EmployeeDto employeeDto) {
        return adapter.convertToEmployeeDto(
                saveEmployee(
                        adapter.convertToEmployee(employeeDto)
                )
        );
    }

    private Employee saveEmployee(Employee employee) {
        em.persist(employee);
        return employee;
    }

    public EmployeeDto findById(UUID id) {
        return adapter.convertToEmployeeDto(em.find(Employee.class, id));
    }

    private Employee findEmployeeById(UUID id) {
        return em.find(Employee.class, id);
    }

    public void deleteById(UUID id) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            em.remove(employee);
        }
    }

    public EmployeeDto update(EmployeeDto employeeDto) {
        Employee employee = em.merge(adapter.convertToEmployee(employeeDto));
        return adapter.convertToEmployeeDto(employee);
    }

    public List<EmployeeDto> findAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class)
                .getResultStream()
                .map(adapter::convertToEmployeeDto)
                .toList();
    }

    public List<EmployeeDto> findByLastName(String nameParam) {
        return em.createNamedQuery("Employee.findByLastName", Employee.class)
                .setParameter("firstName", nameParam)
                .getResultStream()
                .map(adapter::convertToEmployeeDto)
                .toList();
    }
}