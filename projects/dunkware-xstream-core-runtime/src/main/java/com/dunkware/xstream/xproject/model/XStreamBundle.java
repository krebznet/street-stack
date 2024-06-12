package com.dunkware.xstream.xproject.model;


import java.beans.Transient;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.utils.core.json.DunkJson;
import com.dunkware.utils.core.json.bytes.JsonBytes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class XStreamBundle {

	
	private JsonBytes bytes;
	@JsonIgnore
	private XScriptBundle scriptBundle;
	private List<XStreamExtensionType> extensions = new ArrayList<XStreamExtensionType>();
	private LocalDate date; 
	private String timeZone;
	
	
	public XStreamBundle() { 
		
	}
	
	
	

	public List<XStreamExtensionType> getExtensions() {
		return extensions;
	}

	public JsonBytes getBytes() {
		return bytes;
	}

	public void setBytes(JsonBytes bytes) {
		this.bytes = bytes;
		
	}

	@Transient
	public XScriptBundle getScriptBundle() throws JsonMappingException,JsonParseException,IOException {
		if(scriptBundle == null && bytes != null) { 
			this.scriptBundle = DunkJson.getObjectMapper().readValue(bytes.getBytes(), XScriptBundle.class);
		}
		return scriptBundle;
	}

	public void setScriptBundle(XScriptBundle scriptBundle) throws Exception{
		this.scriptBundle = scriptBundle;
		this.bytes = new JsonBytes(DunkJson.getObjectMapper().writeValueAsBytes(scriptBundle));
	}

	public void setExtensions(List<XStreamExtensionType> extensions) {
		this.extensions = extensions;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	

	
	
	
	// time tick -->??? LocalTimeTick.TYPE - HOUR_FIELD = 1; MINUTE_FIELD = 2; 
	
	
}


