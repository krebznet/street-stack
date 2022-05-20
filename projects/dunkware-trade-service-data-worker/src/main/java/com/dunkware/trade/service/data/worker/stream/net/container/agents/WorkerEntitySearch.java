package com.dunkware.trade.service.data.worker.stream.net.container.agents;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.dunkware.net.proto.data.cluster.GContainerEntity;
import com.dunkware.net.proto.netstream.GEntityMatcher;
import com.dunkware.trade.service.data.worker.stream.net.container.WorkerContainer;
import com.dunkware.trade.service.data.worker.stream.net.container.WorkerContainerUtil;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchResults;

public class WorkerEntitySearch implements Runnable {
	
	private WorkerContainer container; 
	private int searchId; 
	private GEntityMatcher matcher; 
	private Logger logger; 
	
	public void start(WorkerContainer container, int searchId, GEntityMatcher matcher, Logger logger) { 
		this.container = container; 
		this.searchId = searchId; 
		this.matcher = matcher; 
		this.logger = logger;
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
		List<GContainerEntity> protoEntities = new ArrayList<GContainerEntity>();
		int i = 0; 
		for (ContainerEntity enity : results.getResults()) {
			try {
				protoEntities.add(WorkerContainerUtil.createProtocolEntity(enity));	
			} catch (Exception e) {
				logger.error("inernal fucked up last values not serializing creating proto entity in entity searhc runnable " + e.toString());
				WorkerContainerUtil.createSearchExceptionMessage("Exception serializing GEntity proto " + e.toString(), container.getReq().getWorkerId());
			}
			
			
			i++;
			if(i > 1000) { 
				container.sendMessage(WorkerContainerUtil.buildSearchResults(protoEntities, searchId, container.getReq().getWorkerId()));
				i = 0;
				protoEntities.clear();
			}
			
		}
		if(protoEntities.size() > 0) { 
			container.sendMessage(WorkerContainerUtil.buildSearchResults(protoEntities, searchId, container.getReq().getWorkerId()));
		}
		container.sendMessage(WorkerContainerUtil.buildEntitySearchComplete(searchId, container.getReq().getWorkerId()));
		// how many results to we send in one batch --> 300; 
		
		
	}
	
	

}
