package com.isa.unasdziala;

import com.isa.unasdziala.cli.MainCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        new MainCli().run();
    }
}
