package com.dunkware.trade.service.data.service.stream.container.connection;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.net.proto.data.cluster.GContainerServerMessage;
import com.dunkware.net.proto.data.cluster.GContainerWorkerMessage;
import com.dunkware.net.proto.netstream.GNetClientMessage;
import com.dunkware.net.proto.netstream.GNetEntity;
import com.dunkware.net.proto.netstream.GNetEntitySearchRequest;
import com.dunkware.net.proto.netstream.GNetEntitySearchResponse;
import com.dunkware.net.proto.netstream.GNetEntitySearchResults;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerController;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerHandler;
import com.dunkware.trade.service.data.service.stream.container.StreamContainerWorker;
import com.dunkware.trade.service.data.util.proto.GContainerProto;

public class EntitySearchRunner extends Thread implements StreamContainerHandler  {

	private StreamContainerConnection connection;
	private GNetEntitySearchRequest request;
	
	private List<GNetEntity> results = new ArrayList<GNetEntity>();
	
	public EntitySearchRunner() { 
		
	}
	
	public void init(GNetEntitySearchRequest request, StreamContainerConnection connection) { 
		this.connection = connection;
		this.request = request;
		
	}
	
	
	public void run() { 
		connection.getController().addMessageHandler(EntitySearchRunner.this);
		GContainerWorkerMessage searchRequestMessage = GContainerProto.searchRequestMessage(request);
		for (StreamContainerWorker worker : connection.getController().getWorkers()) {
			worker.sendMessage(searchRequestMessage);
		}
	}

	@Override
	public void onControllerMessage(GContainerServerMessage message) {
		// how about we send each message notification in its own runnable
		if(GContainerProto.isSearchResponse(message, request.getSearchId())) { 
			GNetEntitySearchResponse resp = message.getEntitySearchResponse();
			// okay this is an acknowlekdgement 
			
		}
		if(GContainerProto.isSearchResponse(message, request.getSearchId())) { 
			GNetEntitySearchResults results = message.getEntitySearchResults();
			//GNetClientMessage.newBuilder().setentitys
		}
	}
	
	
	private class SearchWorker {
		public boolean responded = false; 
		public boolean exception = false;
		// we will just forward the things to the goods. 
		// this happens through a connection
		
	}
	
	
		
	
		
	
	
}
