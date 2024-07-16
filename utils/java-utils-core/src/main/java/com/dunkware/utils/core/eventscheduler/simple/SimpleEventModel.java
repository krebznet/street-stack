package com.dunkware.utils.core.eventscheduler.simple;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleEventModel {
    private String name;
    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<String> days;

    public SimpleEventModel(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("startTime") LocalTime startTime,
            @JsonProperty("endTime") LocalTime endTime,
            @JsonProperty("days") List<String> days) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.days = days;
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

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public List<String> getDays() {
		return days;
	}

	public void setDays(List<String> days) {
		this.days = days;
	}
    
    

    // Getters and Setters
}
