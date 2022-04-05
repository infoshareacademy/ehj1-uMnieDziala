package com.isa.unasdziala.services.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NonWorkingDaysReaderTest {

    @InjectMocks
    private NonWorkingDaysReader sut;

    @Mock
    private String mockedFileName;

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






