package com.dunkware.trade.service.data.json.worker.container;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.xstream.net.core.container.ContainerExtensionType;

public class DataStreamWorkerContainerStartReq {

	private String workerId; 
	private String streamIdentifier;
	private List<ContainerExtensionType> extensionTypes = new ArrayList<ContainerExtensionType>();
	
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public String getStreamIdentifier() {
		return streamIdentifier;
	}
	public void setStreamIdentifier(String streamIdentifier) {
		this.streamIdentifier = streamIdentifier;
	}
	public List<ContainerExtensionType> getExtensionTypes() {
		return extensionTypes;
	}
	public void setExtensionTypes(List<ContainerExtensionType> extensionTypes) {
		this.extensionTypes = extensionTypes;
	} 
	
	
	
	
	
}
