package com.dunkware.utils.core.eventscheduler;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dunkware.utils.core.eventscheduler.model.Event;
import com.dunkware.utils.core.eventscheduler.model.EventOverride;
import com.dunkware.utils.core.eventscheduler.model.EventSchedule;

public class EventController {
    private List<EventSchedule> eventSchedules;
    private List<EventListener> listeners;
    private ZoneId timeZone;
    private Map<String, Boolean> eventStates; // Track event states

    public EventController(ZoneId timeZone) {
        this.eventSchedules = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.timeZone = timeZone;
        this.eventStates = new HashMap<>();
    }

    public void addEventSchedule(EventSchedule eventSchedule) {
        this.eventSchedules.add(eventSchedule);
        // Initialize event states
        for (Event event : eventSchedule.getEvents()) {
            eventStates.put(event.getName(), false);
        }
    }

    public void addListener(EventListener listener) {
        this.listeners.add(listener);
    }

    public void checkEvents() {
        ZonedDateTime now = ZonedDateTime.now(timeZone);
        for (EventSchedule schedule : eventSchedules) {
            for (Event event : schedule.getEvents()) {
                boolean isRunning = isEventRunning(event, now);
                boolean wasRunning = eventStates.get(event.getName());

                if (isRunning && !wasRunning) {
                    // Event started
                    for (EventListener listener : listeners) {
                        listener.onEventStarted(event);
                    }
                    eventStates.put(event.getName(), true);
                } else if (!isRunning && wasRunning) {
                    // Event ended
                    for (EventListener listener : listeners) {
                        listener.onEventEnded(event);
                    }
                    eventStates.put(event.getName(), false);
                }
            }
        }
    }

    private boolean isEventRunning(Event event, ZonedDateTime now) {
        // Check if the event is active considering overrides and time zones
        // Implement the logic to check if the event is running based on the current time

        // Placeholder logic (should be replaced with actual logic)
        for (String day : event.getDays()) {
            if (now.getDayOfWeek().toString().equalsIgnoreCase(day)) {
            	
                if (now.toLocalTime().isAfter(event.getStartTime().toLocalTime())
                        && now.toLocalTime().isBefore(event.getEndTime().toLocalTime())) {
                    // Check for overrides
                    for (EventOverride override : event.getOverrides()) {
                        if (now.isAfter(override.getStartDate()) && now.isBefore(override.getEndDate())) {
                            if (override.isCancelled()) {
                                return false;
                            } else if (override.getEarlyEndTime() != null
                                    && now.toLocalTime().isAfter(override.getEarlyEndTime().toLocalTime())) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
