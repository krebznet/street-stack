package com.dunkware.utils.core.scheduler.simple;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.utils.core.scheduler.model.EventSchedule;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) {
        ZoneId easternTime = ZoneId.of("America/New_York");

        // Create a simple event bean
        SimpleEventModel simplePreMarket = new SimpleEventModel(
                "PreMarket",
                "Pre Market Trading",
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
        );

        // Create a simple event schedule
        SimpleEventSchedule simpleSchedule = new SimpleEventSchedule("USStockMarket", "US Stock Market Trading Hours");
        simpleSchedule.addEvent(simplePreMarket);

        // Convert the simple event schedule to a detailed event schedule
        EventSchedule schedule = SimpleScheduleConverter.convertToEventSchedule(simpleSchedule, easternTime);

        // Serialize to JSON
      
        try {
            String json = DunkJson.getObjectMapper().writeValueAsString(schedule);
            System.out.println("Serialized EventSchedule to JSON:");
            System.out.println(json);

            // Deserialize from JSON
            EventSchedule deserializedSchedule = DunkJson.getObjectMapper().readValue(json, EventSchedule.class);
            System.out.println("Deserialized EventSchedule from JSON:");
            System.out.println(deserializedSchedule.getName());

            // Get the time until the next event starts
            ZonedDateTime now = ZonedDateTime.now(easternTime);
            Duration timeUntilPreMarketStarts = deserializedSchedule.timeUntilEventStarts(schedule.getEvents().get(0), now);
            if (timeUntilPreMarketStarts != null && !timeUntilPreMarketStarts.isZero()) {
                System.out.println("Time until Pre Market starts: " + timeUntilPreMarketStarts.toHours() + " hours " + timeUntilPreMarketStarts.toMinutesPart() + " minutes.");
            } else {
                System.out.println("Pre Market is currently running or has no future start time.");
            }

            // Convert the detailed event schedule back to a simple event schedule
            SimpleEventSchedule simpleDeserializedSchedule = SimpleScheduleConverter.convertToSimpleEventSchedule(deserializedSchedule);
            String simpleJson = DunkJson.getObjectMapper().writeValueAsString(simpleDeserializedSchedule);
            System.out.println("Serialized SimpleEventSchedule to JSON:");
            System.out.println(simpleJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
