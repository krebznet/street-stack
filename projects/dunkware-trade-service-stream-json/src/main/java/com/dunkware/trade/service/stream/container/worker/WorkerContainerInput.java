package com.dunkware.trade.service.stream.container.worker;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.common.util.dtime.DTimeZone;
import com.dunkware.xstream.container.ContainerExtType;

public class WorkerContainerInput {
	
	
	private String workerId; 
	private String streamIdentifier;
	private List<ContainerExtType> containerExtensions = new ArrayList<ContainerExtType>();
	private String kafkaBroker; 
	private String serverTopic; 
	private String workerTopic; 
	private DTimeZone timeZone; 
	
	// give it the stream spec; 
	
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
	
	public String getKafkaBroker() {
		return kafkaBroker;
	}
	public void setKafkaBroker(String kafkaBroker) {
		this.kafkaBroker = kafkaBroker;
	}
	public String getServerTopic() {
		return serverTopic;
	}
	public void setServerTopic(String serverTopic) {
		this.serverTopic = serverTopic;
	}
	
	public String getWorkerTopic() {
		return workerTopic;
	}
	public void setWorkerTopic(String workerTopic) {
		this.workerTopic = workerTopic;
	}
	public List<ContainerExtType> getContainerExtensions() {
		return containerExtensions;
	}
	public void setContainerExtensions(List<ContainerExtType> containerExtensions) {
		this.containerExtensions = containerExtensions;
	}
	public DTimeZone getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(DTimeZone timeZone) {
		this.timeZone = timeZone;
	} 
	
	
	

}
