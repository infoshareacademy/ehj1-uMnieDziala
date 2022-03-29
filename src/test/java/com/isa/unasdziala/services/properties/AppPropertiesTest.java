package com.isa.unasdziala.services.properties;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AppPropertiesTest {

    private AppProperties sut;

    @Test
    void shouldRedFileWithProperties() {
        // given

        // when
        sut = new AppProperties();
        // then
        assertThat(sut).isNotNull();
    }

    @Test
    void shouldGetCountryName() {
        // given
        sut = new AppProperties();
        // whe
        String result = sut.getCountryName();
        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("pl");
    }
}






