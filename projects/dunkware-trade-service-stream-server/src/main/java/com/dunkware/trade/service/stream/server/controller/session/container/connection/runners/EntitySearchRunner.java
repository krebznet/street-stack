package com.dunkware.trade.service.stream.server.controller.session.container.connection.runners;

import java.util.concurrent.atomic.AtomicInteger;

import com.dunkware.net.proto.data.cluster.GContainerServerMessage;
import com.dunkware.net.proto.data.cluster.GContainerWorkerMessage;
import com.dunkware.net.proto.netstream.GNetEntitySearchComplete;
import com.dunkware.net.proto.netstream.GNetEntitySearchException;
import com.dunkware.net.proto.netstream.GNetEntitySearchRequest;
import com.dunkware.net.proto.netstream.GNetEntitySearchResponse;
import com.dunkware.net.proto.netstream.GNetServerMessage;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerHandler;
import com.dunkware.trade.service.stream.server.controller.session.container.SessionContainerWorker;
import com.dunkware.trade.service.stream.server.controller.session.container.connection.SessionContainerConnection;
import com.dunkware.trade.service.stream.util.GContainerProto;

public class EntitySearchRunner extends Thread implements SessionContainerHandler  {

	private SessionContainerConnection connection;
	private GNetEntitySearchRequest request;

	private AtomicInteger workerResponseCountDown = new AtomicInteger(0);
	private AtomicInteger workerCompleteCountDown = new AtomicInteger(0);
	
	public EntitySearchRunner() { 
		
	}
	
	public void init(GNetEntitySearchRequest request, SessionContainerConnection connection) { 
		this.connection = connection;
		this.request = request;
		connection.getController().addMessageHandler(EntitySearchRunner.this);	
	}
	
	
	public void run() { 
		GContainerWorkerMessage searchRequestMessage = GContainerProto.searchRequestMessage(request);
		for (SessionContainerWorker worker : connection.getController().getWorkers()) {
			worker.sendMessage(searchRequestMessage);
			workerResponseCountDown.incrementAndGet();
			workerCompleteCountDown.incrementAndGet();
		}
	}

	@Override
	public void onControllerMessage(GContainerServerMessage message) {
		// how about we send each message notification in its own runnable
		if(GContainerProto.isEntitySearchResponse(message, request.getSearchId())) { 
			GNetEntitySearchResponse resp = message.getEntitySearchResponse();
			int count = workerResponseCountDown.decrementAndGet();
			if(count == 0) { 
				// we received all the worker responses 
				GNetEntitySearchResponse response = GNetEntitySearchResponse.newBuilder().setSearchId(request.getSearchId()).setSource("server").build();
				GNetServerMessage serverResponse = GNetServerMessage.newBuilder().setEntitySearchResponse(response).build();
				connection.sendMessage(serverResponse);
			}
			
		}
		if(GContainerProto.isEntitySearchComplete(message, request.getSearchId())) {
			int count = workerCompleteCountDown.decrementAndGet();
			if(count == 0) { 
				GNetEntitySearchComplete complete = GNetEntitySearchComplete.newBuilder().setSearchId(request.getSearchId()).setSource("server").build();
				GNetServerMessage completeResponse = GNetServerMessage.newBuilder().setEntitySearchComplete(complete).build();
				connection.sendMessage(completeResponse);
			}
		}
		if(GContainerProto.isEntitySearchException(message, request.getSearchId())) { 
			// it could be more than one thing sent. 
			GNetEntitySearchException expetionMessage = GNetEntitySearchException.newBuilder().setSearchId(request.getSearchId()).setException(message.getEntitySearchException().getException()).build();
			GNetServerMessage exceptionResponse = GNetServerMessage.newBuilder().setEntitySearchException(expetionMessage).build();
			connection.sendMessage(exceptionResponse);
			connection.getController().removeMessageHandler(this);
			return;
		}
		if(GContainerProto.isEntitySearchResults(message, request.getSearchId())) { 
			GNetServerMessage searchResults = GNetServerMessage.newBuilder().setEntitySearchResults(message.getEntitySearchResults()).build();
			connection.sendMessage(searchResults);
		}
		
	}
	
	
	
	
	
		
	
		
	
	
}
