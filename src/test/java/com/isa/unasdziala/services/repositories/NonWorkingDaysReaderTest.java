package com.isa.unasdziala.services.repositories;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class NonWorkingDaysReaderTest {

    private NonWorkingDaysReader sut;

    @Test
    void shouldLoadNonWorkingDays() {
        // given

        // when
        sut = new NonWorkingDaysReader();
        // then
        assertThat(sut.getNonWorkingDays()).isNotNull();
        assertThat(sut.getNonWorkingDays()).isInstanceOf(List.class);
    }
}






