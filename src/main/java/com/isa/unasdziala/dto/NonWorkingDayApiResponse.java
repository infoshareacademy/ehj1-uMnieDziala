package com.isa.unasdziala.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.isa.unasdziala.model.NonWorkingDay;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class NonWorkingDayApiResponse implements Serializable {
    private List<NonWorkingDay> nonWorkingDays;

    @JsonProperty("response")
    private void unpackResponseFromNestedObject(Map<String, List<NonWorkingDay>> response) {
        this.nonWorkingDays = response.get("holidays");
    }
}
