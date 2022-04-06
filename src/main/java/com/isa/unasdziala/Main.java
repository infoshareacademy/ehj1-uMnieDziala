package com.isa.unasdziala;

import com.isa.unasdziala.repository.EmployeesImporter;
import com.isa.unasdziala.repository.NonWorkingDaysRepository;
import com.isa.unasdziala.services.NonWorkingDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        EmployeesImporter employeesImporter = new EmployeesImporter();
        NonWorkingDayService nonWorkingDayService = new NonWorkingDayService(new NonWorkingDaysRepository());
         System.out.println("Run...");
        logger.info("\ntest loggera");
        System.out.println(EmployeesImporter.importEmployees());
    }
}
