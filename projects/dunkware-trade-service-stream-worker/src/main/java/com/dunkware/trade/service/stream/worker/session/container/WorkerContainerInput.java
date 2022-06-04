package com.dunkware.trade.service.stream.worker.session.container;

import com.dunkware.common.util.executor.DExecutor;
import com.dunkware.xstream.net.core.container.ContainerInput;

public class WorkerContainerInput {
	
	private ContainerInput streamContainerInput; 
	private String workerId; 
	private DExecutor executor;
	
	public ContainerInput getStreamContainerInput() {
		return streamContainerInput;
	}
	public void setStreamContainerInput(ContainerInput streamContainerInput) {
		this.streamContainerInput = streamContainerInput;
	}
	public String getWorkerId() {
		return workerId;
	}
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	public DExecutor getExecutor() {
		return executor;
	}
	public void setExecutor(DExecutor executor) {
		this.executor = executor;
	} 
	
	

}
