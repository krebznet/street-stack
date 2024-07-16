package com.dunkware.utils.core.eventlogger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventLogger {

	private EventLogger parent;
	private List<EventLogger> children = new ArrayList<EventLogger>();
	private String name;
	private List<String> tags = new ArrayList<String>();
	private Map<String, Object> values = new HashMap<String, Object>();
	private String type; 

	public EventLogger(EventLogger parent, String name, String type, List<String> tags, Map<String,Object> values) {
		this.name = name;
		this.type = type; 
		this.parent = parent;
		if(tags != null)
			this.tags = tags;
		if(values != null) { 
			this.values = values; 
		}
	}
	
	public EventLoggerBuilder loggerBuilder(String name, String type) {
		return new EventLoggerBuilder(this, name, type);
	}
	
	public String getName()  { 
		return name; 
	}
	
	public List<String> getTags() { 
		return tags;
	}
	
	public Map<String,Object> getValues() { 
		return values; 
	}
	
	public boolean hasTag(String tag) { 
		return tags.contains(tag);
	}
	
	public void addTag(String tag) { 
		this.tags.add(tag);
	}
	
	public EventLogger getParent() { 
		return parent; 
	}
	
	public List<EventLogger> getChildren() { 
		return children; 
	}
	
	public void log(String message) { 
		EventLog log = new EventLog();
	}

}
