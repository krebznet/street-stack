package com.dunkware.utils.core.scheduler.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    private String name;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime endTime;

    private List<String> days;
    private List<EventOverride> overrides;

    public Event(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("startTime") ZonedDateTime startTime,
            @JsonProperty("endTime") ZonedDateTime endTime,
            @JsonProperty("days") List<String> days) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
        this.overrides = new ArrayList<>();
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

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(ZonedDateTime endTime) {
		this.endTime = endTime;
	}

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}

	public List<EventOverride> getOverrides() {
		return overrides;
	}

	public void setOverrides(List<EventOverride> overrides) {
		this.overrides = overrides;
	}
    
    

    // Getters and Setters
}