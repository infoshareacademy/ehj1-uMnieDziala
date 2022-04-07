package com.isa.unasdziala.services.properties;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AppPropertiesTest {

    private AppProperties sut;

    @Test
    void shouldRedFileWithProperties() {
        // given

        // when
        sut = new AppProperties();
        // then
        assertThat(sut.getProperties()).isNotNull();
    }

    @Test
    void shouldGetCountryNameFromProperties() {
        // given
        sut = new AppProperties();
        String expectedCountry = "PL";
        // when
        Locale result = sut.getCountryName();
        // then
        assertThat(result).isNotNull();
        assertThat(result.getCountry()).isEqualTo(expectedCountry);
    }
}






