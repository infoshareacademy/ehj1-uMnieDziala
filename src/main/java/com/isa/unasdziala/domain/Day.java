package com.isa.unasdziala.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {
    @JsonIgnore
    private UUID id;
    private LocalDate date;


    @JsonProperty("date")
    private void unpackDateFromNestedObject(Map<String, Object> date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date.get("iso").toString(), formatter);
    }

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    private String country;

    @JsonProperty("country")
    private void unpackCountryFromNestedObject(Map<String, String> country) {
        this.country = country.get("id");
    }

    public Day(LocalDate date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }

    public Day(LocalDate date, String name, String desc, String country) {
        this.date = date;
        this.name = name;
        this.description = desc;
        this.country = country;
    }

    @Override
    public String toString() {
        return "NonWorkingDay:" + date.toString() + "\n\tname: " + name + "\n\tdescription: " + description;
    }
}