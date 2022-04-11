package com.isa.unasdziala.services.properties;

import org.junit.jupiter.api.Test;

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
    void shouldGetMaxAbsenceeFromProperties() {
        // given
        sut = new AppProperties();
        int maxAbsence = 1;
        // when
        int result = sut.getMaxAbsence();
        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(maxAbsence);
    }
}






