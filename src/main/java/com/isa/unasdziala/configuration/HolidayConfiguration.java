package com.isa.unasdziala.configuration;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("holidays")
@ConstructorBinding
@Getter
public class HolidayConfiguration {
    private final int maxAbsence;

    public HolidayConfiguration(int maxAbsence) {
        this.maxAbsence = maxAbsence;
    }
}
