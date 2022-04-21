package com.dunkware.trade.service.stream.server.session;

import java.util.List;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.model.XStreamExtensionType;

public class StreamSessionNodeInput {
	
	
	private List<TradeTickerSpec> tickers;
	private ClusterNode clusterNode; 
	private List<XStreamExtensionType> extensionTypes;
	private StreamSession session;
	private StreamController stream; 
	
	public StreamSessionNodeInput(List<TradeTickerSpec> tickers, ClusterNode clusterNode, List<XStreamExtensionType> extensionTypes, StreamSession session, StreamController stream) {
		this.tickers = tickers;
		this.clusterNode = clusterNode;
		this.extensionTypes = extensionTypes;
		this.session = session;
		this.stream = stream;
	}

	public List<TradeTickerSpec> getTickers() {
		return tickers;
	}

	public ClusterNode getClusterNode() {
		return clusterNode;
	}

	public List<XStreamExtensionType> getExtensionTypes() {
		return extensionTypes;
	}

	public StreamSession getSession() {
		return session;
	}

	public StreamController getStream() {
		return stream;
	}
	
	
	
	
	
	

}
