package com.dunkware.trade.service.stream.server.controller.session;

import java.util.List;

import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;
import com.dunkware.xstream.xproject.model.XStreamBundle;

public class StreamSessionNodeInput {
	
	
	private XStreamBundle streamBundle;
	private List<TradeTickerSpec> tickers;
	private DunkNetNode node;
	private List<StreamSessionExtension> extensionTypes;
	private StreamSession session;
	private StreamController stream; 
	private int numericId;
	private String workerId;
	
	
	public StreamSessionNodeInput(int numericId, String workerId, List<TradeTickerSpec> tickers, DunkNetNode node, List<StreamSessionExtension> extensionTypes, 
			StreamSession session, StreamController stream) {
		this.tickers = tickers;
		this.numericId = numericId;
		this.workerId = workerId;
		this.node = node;
		this.extensionTypes = extensionTypes;
		this.session = session;
		this.stream = stream;
		
	}

	public List<TradeTickerSpec> getTickers() {
		return tickers;
	}

	public DunkNetNode getNode() { 
		return node;
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

	public XStreamBundle getStreamBundle() {
		return streamBundle;
	}

	

	public int getNumericId() {
		return numericId;
	}

	public String getWorkerId() {
		return workerId;
	}

	
	
	
	
	
	
	

}
