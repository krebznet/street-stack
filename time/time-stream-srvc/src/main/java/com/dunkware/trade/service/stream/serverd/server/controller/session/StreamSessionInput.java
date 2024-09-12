package com.dunkware.trade.service.stream.serverd.server.controller.session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.trade.service.stream.serverd.controller.StreamController;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.model.signal.type.XStreamSignalType;

public class StreamSessionInput {
	
	private List<TradeTickerSpec> tickers = new ArrayList<TradeTickerSpec>();
	private StreamController controller; 
	private Vector<DunkNetNode> workerNodes = new Vector<DunkNetNode>();
	private List<XStreamSignalType> signalTypes = new ArrayList<XStreamSignalType>();
	private Map<String,String> properties = new HashMap<String,String>();
	
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
	public List<XStreamSignalType> getSignalTypes() {
		return signalTypes;
	}
	public void setSignalTypes(List<XStreamSignalType> signalTypes) {
		this.signalTypes = signalTypes;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	
	
	
	
	
	

}
