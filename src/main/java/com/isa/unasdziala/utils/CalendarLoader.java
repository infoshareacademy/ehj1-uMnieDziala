package com.isa.unasdziala.utils;

import com.isa.unasdziala.domain.Employee;
import com.isa.unasdziala.domain.Event;
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

public class CalendarLoader {

    public static final DateTimeFormatter ICAL_DATA_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public Set<Event> loadEmployeeEventCalendar(Employee employee) {
        Set<Event> eventDays = new HashSet<>();

        String fileName = employee.getFirstName() + "_" + employee.getLastName() + ".ics";
        URL employeeCalendarURL = Employee.class.getClassLoader().getResource(fileName);

        if (employeeCalendarURL == null) {
            return eventDays;
        }
        String employeeCalendarFilePath = employeeCalendarURL.getPath();

        try (FileInputStream fileInputStream = new FileInputStream(employeeCalendarFilePath)) {
            CalendarBuilder builder = new CalendarBuilder();
            Calendar calendar = builder.build(fileInputStream);
            System.out.println(calendar);

            for (CalendarComponent event : calendar.getComponents(Component.VEVENT)) {
                Event newEvent = convertIcalEventToDomainEvent(event);
                eventDays.add(newEvent);
            }
        } catch (IOException e) {
            System.out.println("Info - no calendar for employee");
        } catch (ParserException e) {
            System.out.println("Wrong data type");
        }
        return eventDays;
    }

    private Event convertIcalEventToDomainEvent(CalendarComponent event) {
        Event newEvent = new Event();
        String eventDateString = event.getProperty(Property.DTSTART).getValue().substring(0, 8);
        LocalDate eventDate = LocalDate.parse(eventDateString, ICAL_DATA_FORMATTER);
        newEvent.setEventDate(eventDate);
        newEvent.setId(UUID.randomUUID());
        return newEvent;
    }
}
