package com.dunkware.utils.core.scheduler.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventSchedule {
    private String name;
    private String description;
    private List<Event> events;

    public EventSchedule(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description) {
        this.name = name;
        this.description = description;
        this.events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    // Method to get the duration until an event starts if it's not currently running
    public Duration timeUntilEventStarts(Event event, ZonedDateTime now) {
        if (isEventRunning(event, now)) {
            return Duration.ZERO;
        }

        ZonedDateTime nextStartTime = getNextStartTime(event, now);
        if (nextStartTime != null) {
            return Duration.between(now, nextStartTime);
        }

        return null; // Event does not have a future start time
    }

    // Helper method to check if the event is currently running
    private boolean isEventRunning(Event event, ZonedDateTime now) {
        for (String day : event.getDays()) {
            if (now.getDayOfWeek().toString().equalsIgnoreCase(day)) {
                ZonedDateTime eventStartTime = event.getStartTime().withYear(now.getYear()).withMonth(now.getMonthValue()).withDayOfMonth(now.getDayOfMonth());
                ZonedDateTime eventEndTime = event.getEndTime().withYear(now.getYear()).withMonth(now.getMonthValue()).withDayOfMonth(now.getDayOfMonth());

                // Check for overrides
                for (EventOverride override : event.getOverrides()) {
                    if (now.isAfter(override.getStartDate()) && now.isBefore(override.getEndDate())) {
                        if (override.isCancelled()) {
                            return false;
                        } else if (override.getEarlyEndTime() != null && now.isAfter(override.getEarlyEndTime())) {
                            return false;
                        }
                    }
                }

                if (now.isAfter(eventStartTime) && now.isBefore(eventEndTime)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to get the next start time of an event
    private ZonedDateTime getNextStartTime(Event event, ZonedDateTime now) {
        ZonedDateTime nextStartTime = null;

        for (String day : event.getDays()) {
            ZonedDateTime potentialStartTime = event.getStartTime().withYear(now.getYear()).withMonth(now.getMonthValue()).withDayOfMonth(now.getDayOfMonth());
            if (now.getDayOfWeek().toString().equalsIgnoreCase(day) && now.isBefore(potentialStartTime)) {
                if (nextStartTime == null || potentialStartTime.isBefore(nextStartTime)) {
                    nextStartTime = potentialStartTime;
                }
            }
        }

        return nextStartTime;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

    
    // Getters and Setters
}
