package com.dunkware.trade.service.data.worker.stream.net.container.agents;

import com.dunkware.net.proto.data.cluster.GEntitySearchException;
import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.trade.service.data.worker.stream.net.container.WorkerContainer;
import com.dunkware.trade.service.data.worker.stream.net.container.WorkerContainerUtil;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchResults;

public class WorkerEntitySearch implements Runnable {
	
	private WorkerContainer container; 
	private int searchId; 
	private GEntityMatcher matcher; 
	
	
	public void start(WorkerContainer container, int searchId, GEntityMatcher matcher) { 
		this.container = container; 
		this.searchId = searchId; 
		this.matcher = matcher; 
	}


	@Override
	public void run() {
		ContainerSearchResults<ContainerEntity> results = null;
		try {
			results = container.getStreamContainer().entitySearch(matcher);
		} catch (Exception e) {
			container.sendMessage(WorkerContainerUtil.createSearchExceptionMessage("Exception executing search " + e.toString(), container.getReq().getWorkerId()));
			return;
			
		}
		// how many results to we send in one batch --> 300; 
		
		
	}
	
	

}
