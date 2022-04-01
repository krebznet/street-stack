package com.dunkware.xstream.model;


import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTimeZone;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class XStreamBundle {

	@JsonProperty(value = "script")
	private XScriptBundle scriptBundle;
	@JsonProperty(value = "extensions")
	private List<XStreamExtensionType> extensions = new ArrayList<XStreamExtensionType>();
	private DDate date; 
	private DTimeZone timeZone;
	
	
	public XStreamBundle() { 
		
	}
	
	@JsonCreator
	public XStreamBundle(@JsonProperty("script") XScriptBundle bundle, @JsonProperty("extensions") List<XStreamExtensionType> extensions, @JsonProperty("date") DDate date) { 
		this.scriptBundle = bundle;
		this.extensions = extensions;
		this.date = date;
	}
	
	public XStreamBundle(XScriptBundle scriptBundle) { 
		this.scriptBundle = scriptBundle;
	}

	public XScriptBundle getScriptBundle() {
		return scriptBundle;
	}

	public void setScriptBundle(XScriptBundle scriptBundle) {
		this.scriptBundle = scriptBundle;
	}
	
	public List<XStreamExtensionType> getExtensions() {
		return extensions;
	}

	public void setExtensions(List<XStreamExtensionType> extensions) {
		this.extensions = extensions;
	}
	
	public void addExtension(XStreamExtensionType type) { 
		this.extensions.add(type);
	}

	public DDate getDate() {
		return date;
	}

	public void setDate(DDate date) {
		this.date = date;
	}

	public DTimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	}
	
	

	
	
	
	// time tick -->??? DTimeTick.TYPE - HOUR_FIELD = 1; MINUTE_FIELD = 2; 
	
	
}


