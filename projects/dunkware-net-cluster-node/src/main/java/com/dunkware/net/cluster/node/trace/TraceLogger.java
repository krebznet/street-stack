package com.dunkware.net.cluster.node.trace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TraceLogger {
	
	private TraceService service; 
	private Class clazz;
	
	private List<String> tags = new ArrayList<String>();
	private Map<String,String> labels = new ConcurrentHashMap<String,String>();
	
	public TraceLogger(TraceService service, Class clazz) { 
		this.service = service;
		this.clazz = clazz;
	}
	
	public void addTag(String tag) { 
		this.tags.add(tag);
		
	}
	
	public void addLabel(String name, String value) { 
		this.labels.put(name, value);
	}
	
	public void info(String message) { 
		Trace trace = new Trace(service,Trace.INFO,clazz);
		trace.message(message);
		if(tags.size() > 0) { 
			for (String tag : tags) {
				trace.tag(tag);
			}
		}
		for (String key : labels.keySet()) {
			trace.label(key, labels.get(key));
		}
		trace.send();
	}
	
	public void info(String message, Object... args) { 
		Trace trace = new Trace(service,Trace.INFO,clazz);
		trace.message(message, args);
		if(tags.size() > 0) { 
			for (String tag : tags) {
				trace.tag(tag);
			}
		}
		for (String key : labels.keySet()) {
			trace.label(key, labels.get(key));
		}
		trace.send();
	}

	public void error(String message) { 
		Trace trace = new Trace(service,Trace.ERROR,clazz);
		
		trace.message(message);
		if(tags.size() > 0) { 
			for (String tag : tags) {
				trace.tag(tag);
			}
		}
		for (String key : labels.keySet()) {
			trace.label(key, labels.get(key));
		}
		trace.send();
	}
	
	public void error(String message, Object... args) { 
		Trace trace = new Trace(service,Trace.ERROR,clazz);
		trace.message(message, args);
		if(tags.size() > 0) { 
			for (String tag : tags) {
				trace.tag(tag);
			}
		}
		for (String key : labels.keySet()) {
			trace.label(key, labels.get(key));
		}
		trace.send();
	}

}
