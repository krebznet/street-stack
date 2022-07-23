package com.dunkware.trade.service.stream.worker.session.container;

import com.dunkware.trade.service.stream.container.worker.WorkerContainerInput;

public interface WorkerContainerService {
	
	
	public WorkerContainer createContainer(WorkerContainerInput input) throws WorkerContainerException;

}
