package com.dunkware.utils.core.scheduler.simple;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SimpleEventSchedule {
    private String name;
    private String description;
    private List<SimpleEventModel> events;

    public SimpleEventSchedule(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("events") List<SimpleEventModel> events) {
        this.name = name;
        this.description = description;
        this.events = events != null ? events : new ArrayList<>();
    }

    public SimpleEventSchedule(String name, String description) {
        this(name, description, new ArrayList<>());
    }

    public void addEvent(SimpleEventModel event) {
        this.events.add(event);
    }

    public List<SimpleEventModel> getEvents() {
        return events;
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

	public void setEvents(List<SimpleEventModel> events) {
		this.events = events;
	}
    
    

    // Getters and Setters
}
