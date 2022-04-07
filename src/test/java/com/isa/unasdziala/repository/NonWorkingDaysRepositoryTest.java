package com.isa.unasdziala.repository;

import com.isa.unasdziala.domain.Day;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class NonWorkingDaysRepositoryTest {

    private NonWorkingDaysRepository sut = new NonWorkingDaysRepository();

    @Test
    void shouldLoadNonWorkingDaysRepository() {
        // when
        sut = new NonWorkingDaysRepository();
        // then
        assertThat(sut.getNonWorkingDays()).isNotNull();
    }

    @Test
    void shouldDayCountryNameBeLikePl() {
        // given
        List<Day> dayList = sut.findAll();
        Day day = dayList.get(1);
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

//    @Test
//    void shouldAddedDayHaveId() {
//        // given
//        Day testDay = getTestDay();
//        // when
//        Day resultDay = sut.add(testDay);
//        // then
//        assertThat(resultDay.getId()).isNotNull();
//    }

//    @Test
//    void shouldNewDayBeSavedInRepositoryWhenAdded() {
//        // given
//        Day testDay = getTestDay();
//        // when
//        Day addedTestDay = sut.add(testDay);
//        List<Day> result = sut.findAll();
//        // then
//        assertThat(result).contains(addedTestDay);
//    }

//    @Test
//    void shouldFindByIdWhenExist() {
//        // given
//        Day testDay = getTestDay();
//        Day addedTestDay = sut.add(testDay);
//        UUID id = addedTestDay.getId();
//
//        // when
//        Optional<Day> resultOptional = sut.findById(id);
//
//        @SuppressWarnings("OptionalGetWithoutIsPresent")
//        Day result = resultOptional.get();
//        // then
//        assertThat(result).isEqualTo(addedTestDay);
//        assertThat(result.getId()).isEqualTo(addedTestDay.getId());
//        assertThat(result.getName()).isEqualTo(addedTestDay.getName());
//        assertThat(result.getDate()).isEqualTo(addedTestDay.getDate());
//        assertThat(result.getDescription()).isEqualTo(addedTestDay.getDescription());
//    }

    @Test
    void shouldFindByIdAlwaysReturnOptional() {
        // when
        Optional<Day> day = sut.findById(UUID.randomUUID());
        // then
        assertThat(day).isInstanceOf(Optional.class);
    }

//    @Test
//    void shouldFindByDateAlwaysReturnListWithDays() {
//        // given
//
//        // when
//        List<Day> result = sut.findDayByLocalDate(LocalDate.now());
//        // then
//        assertThat(result).isNotNull();
//        assertThat(result).isInstanceOf(List.class);
//    }
//
//    @Test
//    void shouldFindByDateReturnDaysWithGivenDate() {
//        // given
//        LocalDate date = LocalDate.parse("1900-01-01");
//        Day testDay1 = new Day(date, "Test Day 1 Name", "Test Day 1 Desc");
//        Day testDay2 = new Day(date, "Test Day 2 Name", "Test Day 2 Desc");
//        Day testDay3 = new Day(LocalDate.parse("1900-01-03"), "Test Day 3 Name", "Test Day 3 Desc");
//        Day testDay4 = new Day(date, "Test Day 4 Name", "Test Day 4 Desc");
//
//        Day expectedTestDay1 = sut.add(testDay1);
//        Day expectedTestDay2 = sut.add(testDay2);
//        Day notExceptedTestDay3 = sut.add(testDay3);
//        Day expectedTestDay4 = sut.add(testDay4);
//
//        List<Day> expected = List.of(expectedTestDay1, expectedTestDay2, expectedTestDay4);
//        // when
//        List<Day> result = sut.findDayByLocalDate(date);
//        // then
//        assertThat(result).containsAll(expected);
//        assertThat(result).doesNotContain(notExceptedTestDay3);
//    }

    @Test
    void shouldBeOptionalEmptyWhenDayWithGivenIdIsNotInRepository() {
        // given
        UUID notExistingId = UUID.fromString("11111111-1111-1111-1111-111111111111");
        // when
        Optional<Day> result = sut.findById(notExistingId);
        // then
        assertThat(result).isEmpty();
    }

//    @Test
//    void shouldUpdateDayWithGivenValues() {
//        // given
//        Day testDay = getTestDay();
//        Day addedTestDay = sut.add(testDay);
//        UUID id = addedTestDay.getId();
//
//        LocalDate newDate = LocalDate.now().plusMonths(1);
//        String newName = "newTestName";
//        String newDesc = "newDesc";
//
//        Day newDay = new Day(newDate, newName, newDesc);
//        // when
//        Optional<Day> optionalResult = sut.updateByDate(id, newDay);
//        @SuppressWarnings("OptionalGetWithoutIsPresent")
//        Day result = optionalResult.get();
//        // then
//        assertThat(result.getId()).isEqualTo(id);
//        assertThat(result.getDate()).isEqualTo(newDate);
//        assertThat(result.getName()).isEqualTo(newName);
//        assertThat(result.getDescription()).isEqualTo(newDesc);
//    }

//    @Test
//    void shouldBeOptionalEmptyWhenTryDeleteNonExistingDay() {
//        // given
//        UUID id = UUID.randomUUID();
//        // when
//        Optional<Day> result = sut.deleteById(id);
//        // then
//        assertThat(result).isEmpty();
//        assertThat(result).isNotNull();
//    }
//
//    @Test
//    void shouldReturnDeletedDayWhenDeleted() {
//        // given
//        Day testDay = getTestDay();
//        Day addedTestDay = sut.add(testDay);
//        UUID id = addedTestDay.getId();
//        // when
//        Optional<Day> optionalResult = sut.deleteById(id);
//        @SuppressWarnings("OptionalGetWithoutIsPresent")
//        Day result = optionalResult.get();
//        // then
//        assertThat(result).isEqualTo(addedTestDay);
//    }
//
//    @Test
//    void shouldDeleteDayWithGivenId() {
//        // given
//        Day testDay = getTestDay();
//        Day addedTestDay = sut.add(testDay);
//        UUID id = addedTestDay.getId();
//        // when
//        sut.deleteById(id);
//        Optional<Day> result = sut.findById(id);
//        // then
//        assertThat(result).isEmpty();
//    }

    private Day getTestDay() {
        return new Day(LocalDate.now(), "TestName", "TestDesc");
    }
}







