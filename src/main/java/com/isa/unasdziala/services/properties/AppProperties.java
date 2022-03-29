package com.isa.unasdziala.services.properties;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Getter
public class AppProperties {
    private final static Logger LOGGER = LoggerFactory.getLogger(AppProperties.class);
    private final static Path PROPERTIES_FILE_PATH = Paths.get("src", "main", "resources", "not_working_days.properties");
    private  static Properties properties;

    public AppProperties() {
        this.properties = readFile(PROPERTIES_FILE_PATH);
    }


    public static String getCountryName(){
        String property = properties.getProperty("country");
        if (property != null){;
        } else {
            LOGGER.info("Properties COUNTRY is empty");
        } return property;
    }

    private static Properties readFile(Path fileName) {
        Properties appProperties = new Properties();
        try {
            appProperties.load(new StringReader(Files.readString(fileName)));
        } catch (InvalidPathException e) {
            LOGGER.error("Path not found, " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("File read error, " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("File error, " + e.getMessage());
        } catch (NullPointerException e) {
            LOGGER.error("File not found, " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Reading properties Error " + e.getMessage());
        }
        return appProperties;
    }
}
