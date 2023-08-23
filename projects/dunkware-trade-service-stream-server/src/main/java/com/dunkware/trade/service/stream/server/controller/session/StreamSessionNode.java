package com.dunkware.trade.service.stream.server.controller.session;

import java.util.List;

import com.dunkware.common.util.events.DEventNode;
import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeState;
import com.dunkware.trade.service.stream.json.controller.session.StreamSessionNodeStatus;
import com.dunkware.trade.service.stream.json.worker.stream.StreamSessionWorkerStats;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.model.snapshot.EntitySnapshot;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public interface StreamSessionNode {

	StreamSessionNodeState getState(); 
	
	StreamSessionWorkerStats getWorkerStats();
	
	ClusterNode getNode(); 
	
	DunkNetNode getDunkNode();
	
	String getNodeId();
	
	List<TradeTickerSpec> getTickers();
	
	boolean hasTicker(String symbol);
	
	void stopNode();
	
	void startNode(StreamSessionNodeInput input) ;
	
	DEventNode getEventNode();
	
	public StreamSessionNodeInput getInput();
	
	public StreamSession getSession();
	
	public StreamController getStream();
	
	public XStreamBundle getStreamBundle();
	
	public String getStartError();
	
	public StreamSessionNodeStatus getStatus();
	
	public EntitySnapshot getEntitySnapshot(String ident) throws Exception;
	
	// yes have a channel - that makes the most sense! 
	// getChannel(); 
	
}
