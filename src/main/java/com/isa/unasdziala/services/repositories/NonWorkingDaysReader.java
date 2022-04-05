package com.isa.unasdziala.services.repositories;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.unasdziala.domain.Day;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class NonWorkingDaysReader {
    public static final String HOLIDAYS_FILE_NAME = "holidays.json";

    private final static Logger LOGGER = LoggerFactory.getLogger(NonWorkingDaysReader.class);
    private List<Day> nonWorkingDays = new ArrayList<Day>();
    ClassLoader classLoader = getClass().getClassLoader();
    private final String NON_WORKING_DAYS_FILE_NAME = "holidays.json";
    private final File NON_WORKING_DAYS_FILE = new File(classLoader.getResource(NON_WORKING_DAYS_FILE_NAME).getFile());
    private final ObjectMapper objectMapper = new ObjectMapper();

    public NonWorkingDaysReader() {
        setNonWorkingDays();
    }

    private void setNonWorkingDays() {
        try {
            LOGGER.info("Start read file: {}" , NON_WORKING_DAYS_FILE_NAME);
            FileReader fileReader = new FileReader(NON_WORKING_DAYS_FILE);
            LOGGER.info("Start load non working days from file");
            List<Day> result = objectMapper
                    .readerFor(new TypeReference<List<Day>>() {})
                    .readValue(fileReader);
            nonWorkingDays.addAll(result);
            LOGGER.info("Have been loaded: {} day/s", result.size());

        } catch (StreamReadException e) {
            LOGGER.error("Error while loading non working days", e);
        } catch (DatabindException e) {
            LOGGER.error("Error while loading non working days", e);
        } catch (IOException e) {
            LOGGER.error("Error while loading non working days", e);
        }
    }
}
