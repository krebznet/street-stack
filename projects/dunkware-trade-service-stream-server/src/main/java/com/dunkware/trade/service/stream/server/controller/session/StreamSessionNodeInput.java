package com.dunkware.trade.service.stream.server.controller.session;

import java.util.List;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.core.StreamSessionNodeCallback;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class StreamSessionNodeInput {
	
	
	private List<TradeTickerSpec> tickers;
	private ClusterNode clusterNode; 
	private List<StreamSessionExtension> extensionTypes;
	private StreamSession session;
	private StreamController stream; 
	private StreamSessionNodeCallback callBack;
	
	public StreamSessionNodeInput(List<TradeTickerSpec> tickers, ClusterNode clusterNode, List<StreamSessionExtension> extensionTypes, 
			StreamSession session, StreamController stream, StreamSessionNodeCallback callBack) {
		this.tickers = tickers;
		this.clusterNode = clusterNode;
		this.extensionTypes = extensionTypes;
		this.session = session;
		this.stream = stream;
		this.callBack = callBack;
	}

	public List<TradeTickerSpec> getTickers() {
		return tickers;
	}

	public ClusterNode getClusterNode() {
		return clusterNode;
	}
	

	public List<StreamSessionExtension> getExtensionTypes() {
		return extensionTypes;
	}

	public StreamSession getSession() {
		return session;
	}

	public StreamController getStream() {
		return stream;
	}

	public StreamSessionNodeCallback getCallBack() {
		return callBack;
	}
	
	
	
	
	
	
	

}
