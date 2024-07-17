package com.dunkware.utils.core.scheduler.simple;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleEventOverride {
    private String startDate; // In the format "yyyy-MM-dd"
    private String endDate;   // In the format "yyyy-MM-dd"
    private boolean isCancelled;
    private String earlyEndTime; // In the format "HH:mm"

    public SimpleEventOverride(
            @JsonProperty("startDate") String startDate,
            @JsonProperty("endDate") String endDate,
            @JsonProperty("isCancelled") boolean isCancelled,
            @JsonProperty("earlyEndTime") String earlyEndTime) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.isCancelled = isCancelled;
        this.earlyEndTime = earlyEndTime;
    }

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String getEarlyEndTime() {
		return earlyEndTime;
	}

	public void setEarlyEndTime(String earlyEndTime) {
		this.earlyEndTime = earlyEndTime;
	}

    // Getters and Setters
    
    
}
