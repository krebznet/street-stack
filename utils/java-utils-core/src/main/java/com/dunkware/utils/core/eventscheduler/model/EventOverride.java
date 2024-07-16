package com.dunkware.utils.core.eventscheduler.model;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventOverride {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime endDate;

    private boolean isCancelled;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime earlyEndTime;

    public EventOverride(
            @JsonProperty("startDate") ZonedDateTime startDate,
            @JsonProperty("endDate") ZonedDateTime endDate,
            @JsonProperty("isCancelled") boolean isCancelled,
            @JsonProperty("earlyEndTime") ZonedDateTime earlyEndTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCancelled = isCancelled;
        this.earlyEndTime = earlyEndTime;
    }

	public ZonedDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(ZonedDateTime startDate) {
		this.startDate = startDate;
	}

	public ZonedDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(ZonedDateTime endDate) {
		this.endDate = endDate;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public ZonedDateTime getEarlyEndTime() {
		return earlyEndTime;
	}

	public void setEarlyEndTime(ZonedDateTime earlyEndTime) {
		this.earlyEndTime = earlyEndTime;
	}
    
    

    // Getters and Setters
}
