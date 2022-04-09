package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.entities.Holiday;
import com.isa.unasdziala.services.repositories.NonWorkingDaysReader;
import com.isa.unasdziala.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class HolidayRepository {

    private final EntityManager em = HibernateUtil.getEntityManager();
    Logger log = LoggerFactory.getLogger(NonWorkingDaysReader.class);

    public List<Holiday> findAll() {
        log.info("Get all holiday day/s from repository");
        return em.createQuery("from Holiday", Holiday.class)
                .getResultStream()
                .toList();
    }
}
