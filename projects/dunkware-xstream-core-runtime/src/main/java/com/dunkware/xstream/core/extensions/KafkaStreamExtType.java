package com.dunkware.xstream.core.extensions;

import com.dunkware.common.util.properties.DProperties;
import com.dunkware.xstream.xproject.model.XStreamExtensionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KafkaStreamExtType extends XStreamExtensionType {

	private DProperties properties;
	private String dataTicks;
	
	public DProperties getProperties() {
		return properties;
	}

	public void setProperties(DProperties properties) {
		this.properties = properties;
	}

	public String getDataTicks() {
		return dataTicks;
	}

	public void setDataTicks(String dataTicks) {
		this.dataTicks = dataTicks;
	}

	
	
	
	
	
}
