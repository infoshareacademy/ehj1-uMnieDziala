package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Holiday;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import com.isa.unasdziala.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class HolidayRepository {

    private final EntityManager em = HibernateUtil.getEntityManager();
    Logger log = LoggerFactory.getLogger(NonWorkingDaysReader.class);

    public Holiday save(Holiday holidayDay) {
        log.info("Add new holiday day to repository");
        em.persist(holidayDay);
        return holidayDay;
    }

    public List<Holiday> findAll() {
        return em.createQuery("from Holiday", Holiday.class)
                .getResultStream()
                .toList();
    }
}
