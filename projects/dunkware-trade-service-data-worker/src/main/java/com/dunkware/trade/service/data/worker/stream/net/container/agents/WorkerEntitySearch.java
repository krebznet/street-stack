package com.dunkware.trade.service.data.worker.stream.net.container.agents;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.dunkware.net.proto.cluster.GContainerServerMessage;
import com.dunkware.net.proto.netstream.GNetEntity;
import com.dunkware.net.proto.netstream.GNetEntitySearchRequest;
import com.dunkware.net.proto.netstream.GNetEntitySearchResults;
import com.dunkware.trade.service.data.worker.stream.net.container.WorkerContainer;
import com.dunkware.xstream.net.core.container.ContainerEntity;
import com.dunkware.xstream.net.core.container.ContainerSearchResults;
import com.dunkware.xstream.net.core.container.util.ContainerHelper;
import com.dunkware.xstream.net.core.util.GNetProto;

public class WorkerEntitySearch implements Runnable {
	
	private WorkerContainer container; 
	private int searchId; 
	private Logger logger; 
	private GNetEntitySearchRequest request;
	
	public WorkerEntitySearch(GNetEntitySearchRequest req, WorkerContainer container) { 
		this.container = container; 
		this.request = req;
		this.searchId = request.getSearchId();
	}

	@Override
	public void run() {
		ContainerSearchResults<ContainerEntity> results = null;
		try {
			// send a response message that the worker is on it
			GContainerServerMessage serverMessage  = GContainerServerMessage.newBuilder().setEntitySearchResponse(GNetProto.entitySearchResponse(searchId, container.getWorkerId())).build();
			container.sendMessage(serverMessage);
			results = container.getStreamContainer().entitySearch(request.getMatcher());
		} catch (Exception e) {
			// okay we crapped out on our search, send entity search exception to the controller 
			GContainerServerMessage response = GContainerServerMessage.newBuilder().setEntitySearchException(GNetProto.entitySearchException(searchId, container.getWorkerId(), e.toString())).build();
			container.sendMessage(response);
			return;
		} 
		// okay we got a set of search results back now lets send them back to the controller. 
		int counter = 0; 
		List<GNetEntity> entities = new ArrayList<GNetEntity>();
		for (ContainerEntity entity : results.getResults()) {
			try {
			entities.add(ContainerHelper.toNetEntity(entity, request.getRetVars()));	
			} catch (Exception e) {
				logger.error("Exception converting to net entity on search id " + request.getSearchId() +  " " + e.toString());
			}
			counter++;
			if(counter == 1000) { 
				GNetEntitySearchResults resultMessage = GNetProto.entitySearchResults(entities, request.getSearchId(),container.getWorkerId());
				container.sendMessage(GContainerServerMessage.newBuilder().setEntitySearchResults(resultMessage).build());
				counter = 0;
				entities.clear();
			}
		}
		if(entities.size() > 0) { 
			GNetEntitySearchResults resultMessage = GNetProto.entitySearchResults(entities, request.getSearchId(),container.getWorkerId());
			container.sendMessage(GContainerServerMessage.newBuilder().setEntitySearchResults(resultMessage).build());
		}
		container.sendMessage(GContainerServerMessage.newBuilder().setEntitySearchComplete(GNetProto.entitySearchComplete(request.getSearchId(), container.getWorkerId())).build());
	
			
			
	
		
	}
	
	

}
