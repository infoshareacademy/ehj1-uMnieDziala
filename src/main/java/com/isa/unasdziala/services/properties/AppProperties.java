package com.isa.unasdziala.services.properties;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Properties;

@Getter
public class AppProperties {
    private final static Logger log = LoggerFactory.getLogger(AppProperties.class);
    ClassLoader classLoader = getClass().getClassLoader();
    private final String PROPERTIES_FILE_NAME = "app.properties";
    private final Properties properties;

    public AppProperties() {
        properties = readFile();
    }


    public Locale getCountryName() {
        Locale locale = new Locale(properties.getProperty("non_working_day_country"), properties.getProperty("non_working_day_country"));
        if (locale != null) {
        } else {
            log.error("Properties 'non_working_day_country' is empty");
        }
        return locale;
    }

    public Integer getMaxAbsence() {
        Integer maxAbsence = Integer.parseInt(properties.getProperty("max_absence"));
        if (maxAbsence != null) {
        } else {
            log.error("Properties 'max_absence' is empty");
        }
        return maxAbsence;
    }

    private Properties readFile() {
        Properties appProperties = new Properties();
        try {
            appProperties.load(new InputStreamReader(classLoader.getResourceAsStream(PROPERTIES_FILE_NAME)));;
        } catch (InvalidPathException e) {
            log.error("Path not found, " + e.getMessage(), e);
        } catch (IOException e) {
            log.error("File read error, " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            log.error("File error, " + e.getMessage(), e);
        } catch (NullPointerException e) {
            log.error("File not found, " + e.getMessage(), e);
        } catch (Exception e) {
            log.error("Reading properties Error " + e.getMessage(), e);
        }
        return appProperties;
    }
}
