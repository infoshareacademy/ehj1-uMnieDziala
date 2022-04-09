package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.entity.Holiday;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import com.isa.unasdziala.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public Optional<Holiday> add(Holiday holiday) {
        Optional<Holiday> existingHoliday = findByDate(holiday.getDate());
//        if (existingHoliday.isPresent()) {
//            System.out.println("The given date already exists in the database");
//            em.getTransaction().begin();
//            em.merge(holiday);
//            em.getTransaction().commit();
//            return Optional.empty();
//        } else {
        System.out.println("Adding date " + holiday.getDate().toString());
        em.getTransaction().begin();
        em.persist(holiday);
        em.getTransaction().commit();
        return Optional.of(holiday);
    }
}

