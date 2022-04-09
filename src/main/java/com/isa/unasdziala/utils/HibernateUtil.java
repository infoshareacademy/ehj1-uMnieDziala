package com.isa.unasdziala.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class HibernateUtil {

    public static final String PERSISTENCE_UNIT_NAME = "example";
    private static final EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();

    public static EntityManager getEntityManager() {
        return em;
    }

}
