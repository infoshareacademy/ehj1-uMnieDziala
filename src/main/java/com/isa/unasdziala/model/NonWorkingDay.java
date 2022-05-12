package com.isa.unasdziala.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonWorkingDay implements Serializable {
    @JsonProperty
    private String name;
    @JsonProperty
    private String description;
    private LocalDate date;

    @JsonProperty("date")
    private void unpackDateFromNestedObject(Map<String, Object> date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(date.get("iso").toString(), formatter);
    }
}
