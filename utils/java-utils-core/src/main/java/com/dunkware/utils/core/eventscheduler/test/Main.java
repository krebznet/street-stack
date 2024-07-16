package com.dunkware.utils.core.eventscheduler.test;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

import javax.swing.event.EventListenerList;

import com.dunkware.utils.core.eventscheduler.EventController;
import com.dunkware.utils.core.eventscheduler.EventListener;
import com.dunkware.utils.core.eventscheduler.model.Event;
import com.dunkware.utils.core.eventscheduler.model.EventSchedule;
import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.utils.core.time.DunkTimeZones;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ZoneId easternTime = ZoneId.of("America/New_York");
        ZonedDateTime now = ZonedDateTime.now(easternTime);

        // Create events
        Event preMarket = new Event("PreMarket", "Pre Market Trading", ZonedDateTime.of(2024, 7, 16, 8, 0, 0, 0, easternTime), ZonedDateTime.of(2024, 7, 16, 9, 0, 0, 0, easternTime), Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        Event regularMarket = new Event("RegularMarket", "Regular Market Trading", ZonedDateTime.of(2024, 7, 16, 9, 30, 0, 0, easternTime), ZonedDateTime.of(2024, 7, 16, 16, 0, 0, 0, easternTime), Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));

        // Create event schedule
        EventSchedule schedule = new EventSchedule("USStockMarket", "US Stock Market Trading Hours");
        schedule.addEvent(preMarket);
        schedule.addEvent(regularMarket);

        // Serialize to JSON
         
        try {
            String json = DunkJson.serialize(schedule);
            System.out.println("Serialized EventSchedule to JSON:");
            System.out.println(json);

            // Deserialize from JSON
            EventSchedule deserializedSchedule = DunkJson.getObjectMapper().readValue(json, EventSchedule.class);
            System.out.println("Deserialized EventSchedule from JSON:");
            System.out.println(deserializedSchedule.getName());
            
            EventController controller = new EventController(DunkTimeZones.zoneNewYork());
            controller.addEventSchedule(deserializedSchedule);
            
          EventListener list =   new EventListener() {
				
				@Override
				public void onEventStarted(Event event) {
					System.out.println(	"Event S" + event.getName() + "Started");

				}
				
				@Override
				public void onEventEnded(Event event) {
					System.out.println(	"Event S" + event.getName() + "Ended");
					
				}
			}; 
			
			controller.addListener(list);
			controller.checkEvents();
			controller.checkEvents();
			
            // Get the time until the next event starts
            Duration timeUntilPreMarketStarts = deserializedSchedule.timeUntilEventStarts(preMarket, now);
            if (timeUntilPreMarketStarts != null && !timeUntilPreMarketStarts.isZero()) {
                System.out.println("Time until Pre Market starts: " + timeUntilPreMarketStarts.toHours() + " hours " + timeUntilPreMarketStarts.toMinutesPart() + " minutes.");
            } else {
                System.out.println("Pre Market is currently running or has no future start time.");
            }

            Duration timeUntilRegularMarketStarts = deserializedSchedule.timeUntilEventStarts(regularMarket, now);
            if (timeUntilRegularMarketStarts != null && !timeUntilRegularMarketStarts.isZero()) {
                System.out.println("Time until Regular Market starts: " + timeUntilRegularMarketStarts.toHours() + " hours " + timeUntilRegularMarketStarts.toMinutesPart() + " minutes.");
            } else {
                System.out.println("Regular Market is currently running or has no future start time.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
