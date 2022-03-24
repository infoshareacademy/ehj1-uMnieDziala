package com.isa.unasdziala;

import com.isa.unasdziala.repository.NonWorkingDaysRepository;
import com.isa.unasdziala.services.NonWorkingDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Main {
public static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        System.out.println("Run...");
        logger.info("\ntest loggera");
        new NonWorkingDayService(new NonWorkingDaysRepository());
    }
}
