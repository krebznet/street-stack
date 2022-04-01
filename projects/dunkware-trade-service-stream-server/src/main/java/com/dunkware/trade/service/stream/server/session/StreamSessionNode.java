package com.dunkware.trade.service.stream.server.session;

import java.util.List;

import com.dunkware.common.util.dtime.DDateTime;
import com.dunkware.common.util.events.DEventNode;
import com.dunkware.trade.service.stream.protocol.controller.session.spec.StreamSessionNodeStatus;
import com.dunkware.trade.service.stream.protocol.controller.session.spec.StreamSessionNodeStatsSpec;
import com.dunkware.trade.service.stream.server.cluster.ClusterNode;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public interface StreamSessionNode {

	StreamSessionNodeStatus getStatus(); 
	
	StreamSessionNodeStatsSpec getStats();
	
	ClusterNode getNode(); 
	
	String getNodeId();
	
	List<TradeTickerSpec> getTickers();
	
	DDateTime getStartedTime();
	
	DDateTime getStartingTime();
	
	int getStartupTime();
	
	void stopNode();
	
	void startNode(StreamSession session, ClusterNode node, String nodeId, List<TradeTickerSpec> tickers); 
	
	XStreamBundle getStreamBundle();
	
	StreamSession getSession();
	
	StreamController getStream();
	
	String getWorkerId();
	
	DEventNode getEventNode();
}
