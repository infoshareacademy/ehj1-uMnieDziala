package com.isa.unasdziala.repository;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NonWorkingDaysRepositoryTest {

    private NonWorkingDaysRepository sut;

    @Test
    void shouldLoadNonWorkingDaysRepository() {
        // given

        // when
        sut = new NonWorkingDaysRepository();
        // then
        assertThat(sut.getNonWorkingDays()).isNotNull();
        System.out.println(sut.getNonWorkingDays().size());
        sut.getNonWorkingDays().stream().forEach(day -> System.out.println(day.toString()));

    }
}






