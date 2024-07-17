package com.dunkware.utils.core.scheduler.simple;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.utils.core.scheduler.model.Event;
import com.dunkware.utils.core.scheduler.model.EventOverride;
import com.dunkware.utils.core.scheduler.model.EventSchedule;

public class SimpleScheduleConverter {

    public static Event convertToEvent(SimpleEventModel simpleEventModel, ZoneId timeZone) {
        List<EventOverride> overrides = new ArrayList<>();
        ZonedDateTime startTime = ZonedDateTime.of(
                ZonedDateTime.now(timeZone).getYear(),
                ZonedDateTime.now(timeZone).getMonthValue(),
                ZonedDateTime.now(timeZone).getDayOfMonth(),
                simpleEventModel.getStartTime().getHour(),
                simpleEventModel.getStartTime().getMinute(),
                0,
                0,
                timeZone
        );

        ZonedDateTime endTime = ZonedDateTime.of(
                ZonedDateTime.now(timeZone).getYear(),
                ZonedDateTime.now(timeZone).getMonthValue(),
                ZonedDateTime.now(timeZone).getDayOfMonth(),
                simpleEventModel.getEndTime().getHour(),
                simpleEventModel.getEndTime().getMinute(),
                0,
                0,
                timeZone
        );

        return new Event(
                simpleEventModel.getName(),
                simpleEventModel.getDescription(),
                startTime,
                endTime,
                simpleEventModel.getDays()
        );
    }

    public static EventOverride convertToEventOverride(SimpleEventOverride simpleEventOverride, ZoneId timeZone) {
        ZonedDateTime startDate = ZonedDateTime.of(LocalDate.parse(simpleEventOverride.getStartDate()).atStartOfDay(), timeZone);
        ZonedDateTime endDate = ZonedDateTime.of(LocalDate.parse(simpleEventOverride.getEndDate()).atStartOfDay(), timeZone);
        ZonedDateTime earlyEndTime = simpleEventOverride.getEarlyEndTime() != null ?
                ZonedDateTime.of(LocalDate.now(), LocalTime.parse(simpleEventOverride.getEarlyEndTime()), timeZone) : null;

        return new EventOverride(
                startDate,
                endDate,
                simpleEventOverride.isCancelled(),
                earlyEndTime
        );
    }

    public static EventSchedule convertToEventSchedule(SimpleEventSchedule simpleEventSchedule, ZoneId timeZone) {
        EventSchedule eventSchedule = new EventSchedule(simpleEventSchedule.getName(), simpleEventSchedule.getDescription());

        for (SimpleEventModel simpleEventModel : simpleEventSchedule.getEvents()) {
            Event event = convertToEvent(simpleEventModel, timeZone);
            eventSchedule.addEvent(event);
        }

        return eventSchedule;
    }

    public static SimpleEventModel convertToSimpleEventModel(Event event) {
        LocalTime startTime = event.getStartTime().toLocalTime();
        LocalTime endTime = event.getEndTime().toLocalTime();

        return new SimpleEventModel(
                event.getName(),
                event.getDescription(),
                startTime,
                endTime,
                event.getDays()
        );
    }

    public static SimpleEventOverride convertToSimpleEventOverride(EventOverride eventOverride) {
        String startDate = eventOverride.getStartDate().toLocalDate().toString();
        String endDate = eventOverride.getEndDate().toLocalDate().toString();
        String earlyEndTime = eventOverride.getEarlyEndTime() != null ? eventOverride.getEarlyEndTime().toLocalTime().toString() : null;

        return new SimpleEventOverride(
                startDate,
                endDate,
                eventOverride.isCancelled(),
                earlyEndTime
        );
    }

    public static SimpleEventSchedule convertToSimpleEventSchedule(EventSchedule eventSchedule) {
        SimpleEventSchedule simpleEventSchedule = new SimpleEventSchedule(eventSchedule.getName(), eventSchedule.getDescription());

        for (Event event : eventSchedule.getEvents()) {
            SimpleEventModel simpleEventModel = convertToSimpleEventModel(event);
            simpleEventSchedule.addEvent(simpleEventModel);
        }

        return simpleEventSchedule;
    }
}
