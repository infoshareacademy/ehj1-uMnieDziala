package com.isa.unasdziala.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {
    @JsonIgnore
    private Long id;

    private LocalDate date;

    @JsonProperty("date")
    private void unpackDateFromNestedObject(Map<String, Object> date) throws ParseException {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
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

    public Day() {
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", date=" + date.toString() +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}