package com.dunkware.trade.net.data.server.stream.entitystats;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dunkware.spring.cluster.DunkNet;
import com.dunkware.spring.cluster.anot.ADunkNetService;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggRequest;
import com.dunkware.trade.service.data.model.entitystats.EntityStatAggResponse;

import io.vertx.core.Future;
import io.vertx.core.Promise;

public class StreamEntityStatCluster {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	private Marker marker = MarkerFactory.getMarker("EntityStatCluster");
	
	 @Autowired
	 private DunkNet dnnkNet;
	
	 private BlockingQueue<QueuedEntityStatsAggRequest> aggRequestQueue = new LinkedBlockingQueue<QueuedEntityStatsAggRequest>();
	 
	 
	 @ADunkNetService(label = "Entity Stat Agg Request Service, The Hot one")
	 private Future<EntityStatAggResponse> entityStatAggRequest(EntityStatAggRequest request) { 
		 QueuedEntityStatsAggRequest queuedAggRequest = new QueuedEntityStatsAggRequest(request);
		 aggRequestQueue.add(queuedAggRequest);
		 return queuedAggRequest.getFuture();
	 }
	 
	 
	 private class QueuedEntityStatsAggRequest { 
		 
		 private EntityStatAggRequest req; 
		 private Promise<EntityStatAggResponse> promise; 
		 
		 public QueuedEntityStatsAggRequest(EntityStatAggRequest req) { 
			 promise = Promise.promise();
			 this.req = req;
		 }
		 
		 public Future<EntityStatAggResponse> getFuture() { 
			 return promise.future();
		 }
		 
		 public EntityStatAggRequest getRequest() { 
			 return req; 
		 }
		 
		 private void setEntityAggResponse(EntityStatAggResponse response) { 
			 response.setRequestId(req.getRequest());;
			 promise.complete(response);
		 }
	 }
	 
	 
	 

}
