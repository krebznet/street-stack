package com.dunkware.trade.service.stream.server.controller.session.xstream;

import com.dunkware.xstream.model.XStreamExtensionType;

public class ControllerNotifyExtType extends XStreamExtensionType {
	
	private String endpoint;
	private String node; 
	private String stream; 
	
	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}
	
	
	
	
	
	

}
