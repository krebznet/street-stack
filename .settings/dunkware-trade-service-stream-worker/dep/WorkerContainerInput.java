package com.dunkware.trade.service.stream.worker.session.container.core;

import com.dunkware.common.util.executor.DunkExecutor;
import com.dunkware.xstream.net.core.container.ContainerInput;

public class WorkerContainerInput {
	
	private ContainerInput streamContainerInput; 
	private String workerId; 
	private DunkExecutor executor;
	
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
	public DunkExecutor getExecutor() {
		return executor;
	}
	public void setExecutor(DunkExecutor executor) {
		this.executor = executor;
	} 
	
	

}
