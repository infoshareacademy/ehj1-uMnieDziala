package com.isa.unasdziala.configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties("non-working-days-client")
@ConstructorBinding
@Getter
@AllArgsConstructor
public class NonWorkingDaysConfiguration {
    private String baseUrl;
    private String apiKey;
    private String country;
    private String type;


}
