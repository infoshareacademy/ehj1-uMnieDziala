package com.isa.unasdziala;

import com.isa.unasdziala.dao.EmployeeDao;
import com.isa.unasdziala.repository.EmployeesRepository;
import com.isa.unasdziala.repository.NonWorkingDaysRepository;
import com.isa.unasdziala.services.NonWorkingDayService;
import com.isa.unasdziala.services.EmployeeServiceBase;
import com.isa.unasdziala.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Run...");
        logger.info("\ntest loggera");
        System.out.println(EmployeesRepository.importEmployees());
        new Main().run();


        EmployeesRepository employeesRepository = new EmployeesRepository();
        NonWorkingDayService nonWorkingDayService = new NonWorkingDayService(new NonWorkingDaysRepository());
        System.out.println();
    }

    private void run() {
        EntityManager em = HibernateUtil.getEntityManager();
        EmployeeServiceBase service = new EmployeeServiceBase(new EmployeeDao());
    }

}
