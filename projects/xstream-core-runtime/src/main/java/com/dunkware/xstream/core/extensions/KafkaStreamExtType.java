package com.dunkware.xstream.core.extensions;

import java.util.Map;

import com.dunkware.xstream.xproject.model.XStreamExtensionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaStreamExtType extends XStreamExtensionType {

	private Map<String,String> properties;
	private String dataTicks;
	

	public Map<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}

	public String getDataTicks() {
		return dataTicks;
	}

	public void setDataTicks(String dataTicks) {
		this.dataTicks = dataTicks;
	}

	
	
	
	
	
}
