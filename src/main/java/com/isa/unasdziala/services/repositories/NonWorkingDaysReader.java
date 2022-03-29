package com.isa.unasdziala.services.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.services.properties.AppProperties;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Getter
public class NonWorkingDaysReader {
    public static final String HOLIDAYS_FILE_NAME = "holidays.json";

    private final static Logger LOGGER = LoggerFactory.getLogger(NonWorkingDaysReader.class);
    private List<Day> nonWorkingDays;
    private final Path usersPath = Paths.get(NonWorkingDaysReader.class.getClassLoader().getResource(HOLIDAYS_FILE_NAME).getPath());
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateDeserializer("yyyy-MM-dd"))
            .create();

    public NonWorkingDaysReader() {
        getNonWorkingDays();
    }

    private void getNonWorkingDays(){
        try {
            FileReader fileReader = new FileReader(usersPath.toString());
            nonWorkingDays = gson.fromJson(fileReader, List.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
