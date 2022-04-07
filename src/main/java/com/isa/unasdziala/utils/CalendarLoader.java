package com.isa.unasdziala.utils;

import com.isa.unasdziala.domain.Day;
import com.isa.unasdziala.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.CalendarComponent;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Slf4j
public class CalendarLoader {

    public static final DateTimeFormatter ICAL_DATA_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public Set<Day> loadEmployeeEventCalendar(Employee employee) {
        Set<Day> eventDays = new HashSet<>();

        String fileName = employee.getFirstName() + "_" + employee.getLastName() + ".ics";
        URL employeeCalendarURL = Employee.class.getClassLoader().getResource(fileName);

        if (employeeCalendarURL == null) {
            return eventDays;
        }
        String employeeCalendarFilePath = employeeCalendarURL.getPath();

        try (FileInputStream fileInputStream = new FileInputStream(employeeCalendarFilePath)) {
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(fileInputStream);
            for (CalendarComponent event : calendar.getComponents(Component.VEVENT)) {
                Day newEvent = convertIcalEventToDomainEvent(event);
                eventDays.add(newEvent);
            }
        } catch (IOException e) {
            log.info("Cannot load file for employer - " + employeeCalendarFilePath);
        } catch (ParserException e) {
            log.info("Calendar builder parse error ", e);
        }
        return eventDays;
    }

    private Day convertIcalEventToDomainEvent(CalendarComponent event) {
        Day newEvent = new Day();
        String eventDateString = event.getProperty(Property.DTSTART).getValue().substring(0, 8);
        LocalDate eventDate = LocalDate.parse(eventDateString, ICAL_DATA_FORMATTER);
        newEvent.setDate(eventDate);
        newEvent.setId(UUID.randomUUID());
        return newEvent;
    }
}
