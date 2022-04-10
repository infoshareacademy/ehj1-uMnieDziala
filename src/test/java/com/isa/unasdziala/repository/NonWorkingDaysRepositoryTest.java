package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class NonWorkingDaysRepositoryTest {

    private NonWorkingDaysRepository sut = new NonWorkingDaysRepository();

    @BeforeEach
    void setUp() {
        sut = new NonWorkingDaysRepository();
    }

    @Test
    void shouldLoadNonWorkingDaysRepository() {
        // when

        // then
        assertThat(sut.findAll()).isNotEmpty();
    }

    @Test
    void shouldDayCountryNameBeLikePl() {
        // given

        List<Day> dayList = sut.findAll();
        Day day = dayList.get(0);
        // when
        String result = day.getCountry();
        // then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo("pl");
    }

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
        Optional<Day> addedDayOptional = sut.add(testDay);
        Day resultDay = addedDayOptional.get();
        // then
        assertThat(resultDay.getId()).isNotNull();
        assertThat(resultDay.getId()).isInstanceOf(UUID.class);
    }

    @Test
    void shouldNewDayBeSavedInRepositoryWhenAdded() {
        // given
        Day testDay = getTestDay();
        // when
        Day addedTestDay = sut.add(testDay).get();
        List<Day> result = sut.findAll();
        // then
        assertThat(result).contains(addedTestDay);
    }

    @Test
    void shouldFindByDateAlwaysReturnOptional() {
        // when
        Optional<Day> result = sut.findByDate(LocalDate.now());
        // then
        assertThat(result).isInstanceOf(Optional.class);
    }

    @Test
    void shouldFindByDateReturnOptionalDayWithGivenDate() {
        // given
        LocalDate date = LocalDate.parse("1900-01-01");
        Day testDay = new Day(date, "Test Day 1 Name", "Test Day 1 Desc");
        sut.add(testDay);

        // when
        Day result = sut.findByDate(date).get();
        // then
        assertThat(result.getDate()).isEqualTo(testDay.getDate());
        assertThat(result.getName()).isEqualTo(testDay.getName());
        assertThat(result.getDescription()).isEqualTo(testDay.getDescription());
    }

    @Test
    void shouldUpdateDayWithGivenValues() {
        // given
        Day testDay = getTestDay();
        Day addedTestDay = sut.add(testDay).get();
        LocalDate date = addedTestDay.getDate();

        String newName = "newTestName";
        String newDesc = "newDesc";

        Day newDay = new Day(date, newName, newDesc);
        // when
        Optional<Day> optionalResult = sut.updateByDate(newDay);
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Day result = optionalResult.get();
        // then
        assertThat(result.getName()).isEqualTo(newName);
        assertThat(result.getDescription()).isEqualTo(newDesc);
    }

    @Test
    void shouldBeOptionalEmptyWhenTryDeleteNonExistingDay() {
        // given
        LocalDate nonExistingDate = LocalDate.parse("3333-03-03");
        // when
        Optional<Day> result = sut.deleteByDate(nonExistingDate);
        // then
        assertThat(result).isEmpty();
        assertThat(result).isNotNull();
    }

    @Test
    void shouldReturnDeletedDayWhenDeleted() {
        // given
        Day addedTestDay = sut.add(getTestDay()).get();
        LocalDate date = addedTestDay.getDate();
        // when
        Optional<Day> optionalResult = sut.deleteByDate(date);
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Day result = optionalResult.get();
        // then
        assertThat(result).isEqualTo(addedTestDay);
    }

    @Test
    void shouldDeleteDayWithGivenId() {
        // given

        Day addedTestDay = sut.add(getTestDay()).get();
        LocalDate date = addedTestDay.getDate();
        // when
        sut.deleteByDate(date);
        Optional<Day> result = sut.findByDate(date);
        // then
        assertThat(result).isEmpty();
    }

    private Day getTestDay() {
        return new Day(LocalDate.now(), "TestName", "TestDesc");
    }
}







