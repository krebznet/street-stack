package com.dunkware.trade.service.stream.server.controller.session;

import java.util.ArrayList;
import java.util.List;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class StreamSessionInput {
	
	private List<TradeTickerSpec> tickers = new ArrayList<TradeTickerSpec>();
	private StreamController controller; 
	private List<ClusterNode> workerNodes = new ArrayList<ClusterNode>();
	
	public List<TradeTickerSpec> getTickers() {
		return tickers;
	}
	public void setTickers(List<TradeTickerSpec> tickers) {
		this.tickers = tickers;
	}
	public StreamController getController() {
		return controller;
	}
	public void setController(StreamController controller) {
		this.controller = controller;
	}
	public List<ClusterNode> getWorkerNodes() {
		return workerNodes;
	}
	public void setWorkerNodes(List<ClusterNode> workerNodes) {
		this.workerNodes = workerNodes;
	}
	
	

}
