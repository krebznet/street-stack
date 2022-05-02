package com.dunkware.xstream.xproject.model;


import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DDate;
import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.common.util.json.DJson;
import com.dunkware.common.util.json.bytes.DBytes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class XStreamBundle {

	
	private DBytes bytes;
	@JsonIgnore
	private XScriptBundle scriptBundle;
	private List<XStreamExtensionType> extensions = new ArrayList<XStreamExtensionType>();
	private DDate date; 
	private DTimeZone timeZone;
	
	
	public XStreamBundle() { 
		
	}
	
	/*
	 * @JsonCreator public XStreamBundle(@JsonProperty("script") XScriptBundle
	 * bundle, @JsonProperty("extensions") List<XStreamExtensionType>
	 * extensions, @JsonProperty("date") DDate date) { this.scriptBundle = bundle;
	 * this.extensions = extensions; this.date = date; }
	 */
	

	public List<XStreamExtensionType> getExtensions() {
		return extensions;
	}

	public DBytes getBytes() {
		return bytes;
	}

	public void setBytes(DBytes bytes) {
		this.bytes = bytes;
		
	}

	@Transient
	public XScriptBundle getScriptBundle() throws JsonMappingException,JsonParseException,IOException {
		if(scriptBundle == null && bytes != null) { 
			this.scriptBundle = DJson.getObjectMapper().readValue(bytes.getBytes(), XScriptBundle.class);
		}
		return scriptBundle;
	}

	public void setScriptBundle(XScriptBundle scriptBundle) throws Exception{
		this.scriptBundle = scriptBundle;
		this.bytes = new DBytes(DJson.getObjectMapper().writeValueAsBytes(scriptBundle));
	}

	public void setExtensions(List<XStreamExtensionType> extensions) {
		this.extensions = extensions;
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


