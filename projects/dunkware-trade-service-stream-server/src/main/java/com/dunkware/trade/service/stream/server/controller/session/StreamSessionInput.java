package com.dunkware.trade.service.stream.server.controller.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class StreamSessionInput {
	
	private List<TradeTickerSpec> tickers = new ArrayList<TradeTickerSpec>();
	private StreamController controller; 
	private Vector<DunkNetNode> workerNodes = new Vector<DunkNetNode>();
	
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
	public Vector<DunkNetNode> getWorkerNodes() {
		return workerNodes;
	}
	public void setWorkerNodes(Vector<DunkNetNode> workerNodes) {
		this.workerNodes = workerNodes;
	}
	
	
	
	

}
