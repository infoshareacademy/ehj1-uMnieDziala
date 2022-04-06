package com.isa.unasdziala.utils;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CalendarLoaderTest {

    private CalendarLoader sut = new CalendarLoader();

   @Test
   void shouldReturnEmptyListWhenFileDoesNotExists() {
      // given
      Employee testEmployee = new Employee("Not", "Existing", null, null, null, 0);
      // when
      Set<Day> result = sut.loadEmployeeEventCalendar(testEmployee);
      // then
      assertThat(result).isEmpty();
      assertThat(result).isNotNull();
   }

   @Test
   void shouldReturnListWith3ElementsWhenLoadedTestFile() {
      // given
      Employee testEmployee = new Employee("TestName", "TestLastName", null, null, null, 0);
      // when
      Set<Day> result = sut.loadEmployeeEventCalendar(testEmployee);
      // then
      assertThat(result).hasSize(3);
   }
}