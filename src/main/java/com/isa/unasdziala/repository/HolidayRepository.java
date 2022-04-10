package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.entity.Employee;
import com.isa.unasdziala.domain.entity.Holiday;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import com.isa.unasdziala.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class HolidayRepository {

    private final EntityManager em = HibernateUtil.getEntityManager();
    Logger log = LoggerFactory.getLogger(NonWorkingDaysReader.class);

    public List<Holiday> findAll() {
        log.info("Get all holiday day/s from repository");
        return em.createQuery("from Holiday", Holiday.class)
                .getResultStream()
                .toList();
    }

    public Optional<Holiday> findByDate(LocalDate date) {
        return em.createNamedQuery("Holiday.findByDate", Holiday.class)
                .setParameter("date", date)
                .getResultStream()
                .findFirst();
    }

    public Optional<Holiday> add(LocalDate date, Employee employee) {
        Optional<Holiday> existingHoliday = findByDate(date);
        Set<Holiday> holidaysFromEmployee = employee.getHolidayDays();
        System.out.println(holidaysFromEmployee);
        if (holidaysFromEmployee.contains(date)) {
            if (existingHoliday.isPresent()) {
                System.out.println("Date exist " + existingHoliday.get().getDate().toString());
                existingHoliday.get().getEmployees().add(employee);
                holidaysFromEmployee.add(existingHoliday.get());
                em.getTransaction().begin();
                em.merge(existingHoliday.get());
                em.getTransaction().commit();
                return existingHoliday;
            } else {
                System.out.println("już jest");
                return existingHoliday;
            }
        } else {
            Holiday holiday = new Holiday(date);
            holiday.getEmployees().add(employee);
            holidaysFromEmployee.add(holiday);
            System.out.println("Adding date " + date.toString());
            em.getTransaction().begin();
            em.merge(holiday);
            em.getTransaction().commit();
            return Optional.of(holiday);
        }
    }

    private List<LocalDate> getDatesOfHolidays(LocalDate date, Employee employee) {
        return findByDate(date).stream()
                .filter(h -> h.getEmployees().contains(employee))
                .map(h -> h.getDate())
                .collect(Collectors.toList());
    }

}

