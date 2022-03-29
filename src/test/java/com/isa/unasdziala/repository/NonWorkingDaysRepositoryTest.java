package com.isa.unasdziala.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Slf4j
class NonWorkingDaysRepositoryTest {

    private NonWorkingDaysRepository sut = new NonWorkingDaysRepository();

    @Test
    void shouldLoadPropertiesWhenFileExists() {
        // given
        String file = "/not_working_days.properties";
        // when
        sut.loadProps(file);
        // then
        assertThat(sut.getCountry()).isEqualTo("pl");
    }

    @Test
    void shouldLoad() {
        // given
        String file = "src/main/resources/holidays.json";
        // when
        sut.importNonWorkingDays(file);
        // then
        assertThat(sut.getNonWorkingDays()).isNotNull();
    }
}






