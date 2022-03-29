package com.isa.unasdziala.services.repositories;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.isa.unasdziala.services.properties.AppProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {

    private final static Logger LOGGER = LoggerFactory.getLogger(LocalDateDeserializer.class);
    private final String pattern;

    public LocalDateDeserializer(String pattern) {
        LOGGER.info("Set patern to local date deserializer");
        this.pattern = pattern;
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        return LocalDate.parse(json.getAsString(),
                DateTimeFormatter.ofPattern(pattern));
    }
}