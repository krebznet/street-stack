package com.dunkware.utils.core.eventlogger;

import java.util.List;
import java.util.Map;

public class EventLoggerBuilder {

	private EventLogger parent;
	private String name;
	private List<String> tags;
	private Map<String, Object> values;
	private String type;

	public EventLoggerBuilder(EventLogger parent, String name, String type) {
		this.parent = parent;
		this.name = name;
		this.type = type;
	}

	public EventLoggerBuilder(EventLogger parent, String name, String type, String... tags) {
		this.parent = parent;
		this.name = name;
		this.type = type;
		for (String string : tags) {
			this.tags.add(string);
		}
	}

	public EventLoggerBuilder value(String key, Object value) {
		this.values.put(key, value);
		return this;
	}

	public EventLoggerBuilder tags(String... tag) {
		for (String string : tag) {
			this.tags.add(string);
		}
		return this;
	}

	public EventLogger build() {
		EventLogger logger = new EventLogger(parent, name, type, tags, values);
		if(parent != null) { 
			parent.getChildren().add(logger);
		}
		return logger;
	}

}