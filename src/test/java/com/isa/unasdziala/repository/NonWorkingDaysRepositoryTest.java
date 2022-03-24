package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class NonWorkingDaysRepositoryTest {

    private final NonWorkingDaysRepository sut = new NonWorkingDaysRepository();

    @Test
    void shouldFindAllAlwaysReturnList() {
        // when
        List<Day> dayList = sut.findAll();
        // then
        assertThat(dayList).isNotNull();
        assertThat(dayList).isInstanceOf(List.class);
    }

    @Test
    void shouldAddedDayHaveId() {
        // given
        Day testDay = getTestDay();
        // when
        Day resultDay = sut.add(testDay);
        // then
        assertThat(resultDay.getId()).isNotNull();
    }

    @Test
    void shouldNewDayBeSavedInRepositoryWhenAdded() {
        // given
        Day testDay = getTestDay();
        // when
        Day addedTestDay = sut.add(testDay);
        List<Day> result = sut.findAll();
        // then
        assertThat(result).contains(addedTestDay);
    }

    @Test
    void shouldFindByIdWhenExist() {
        // given
        Day testDay = getTestDay();
        Day addedTestDay = sut.add(testDay);
        Long id = addedTestDay.getId();

        // when
        Optional<Day> resultOptional = sut.findById(id);

        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Day result = resultOptional.get();
        // then
        assertThat(result).isEqualTo(addedTestDay);
        assertThat(result.getId()).isEqualTo(addedTestDay.getId());
        assertThat(result.getName()).isEqualTo(addedTestDay.getName());
        assertThat(result.getDate()).isEqualTo(addedTestDay.getDate());
        assertThat(result.getDescription()).isEqualTo(addedTestDay.getDescription());
    }

    @Test
    void shouldFindByIdAlwaysReturnOptional() {
        // when
        Optional<Day> day = sut.findById(-1L);
        // then
        assertThat(day).isInstanceOf(Optional.class);
    }

    @Test
    void shouldBeOptionalEmptyWhenDayWithGivenIdIsNotInRepository() {
        // given
        long notExistingId = -1L;
        // when
        Optional<Day> result = sut.findById(notExistingId);
        // then
        assertThat(result).isEmpty();
    }

    @Test
    void shouldUpdateDayWithGivenValues() {
        // given
        Day testDay = getTestDay();
        Day addedTestDay = sut.add(testDay);
        Long id = addedTestDay.getId();

        LocalDate newDate = LocalDate.now().plusMonths(1);
        String newName = "newTestName";
        String newDesc = "newDesc";

        Day newDay = new Day(newDate, newName, newDesc);
        // when
        Optional<Day> optionalResult = sut.updateById(id, newDay);
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Day result = optionalResult.get();
        // then
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getDate()).isEqualTo(newDate);
        assertThat(result.getName()).isEqualTo(newName);
        assertThat(result.getDescription()).isEqualTo(newDesc);
    }

    @Test
    void shouldBeOptionalEmptyWhenTryDeleteNonExistingDay() {
        // given
        Long id = -1L;
        // when
        Optional<Day> result = sut.deleteById(id);
        // then
        assertThat(result).isEmpty();
        assertThat(result).isNotNull();
    }

    @Test
    void shouldReturnDeletedDayWhenDeleted() {
        // given
        Day testDay = getTestDay();
        Day addedTestDay = sut.add(testDay);
        Long id = addedTestDay.getId();
        // when
        Optional<Day> optionalResult = sut.deleteById(id);
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Day result = optionalResult.get();
        // then
        assertThat(result).isEqualTo(addedTestDay);
    }

    @Test
    void shouldDeleteDayWithGivenId() {
        // given
        Day testDay = getTestDay();
        Day addedTestDay = sut.add(testDay);
        Long id = addedTestDay.getId();
        // when
        sut.deleteById(id);
        Optional<Day> result = sut.findById(id);
        // then
        assertThat(result).isEmpty();
    }

    private Day getTestDay() {
        return new Day(LocalDate.now(), "TestName", "TestDesc");
    }
}
