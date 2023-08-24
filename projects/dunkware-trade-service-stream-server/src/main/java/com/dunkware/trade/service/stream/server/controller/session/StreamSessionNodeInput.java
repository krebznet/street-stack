package com.dunkware.trade.service.stream.server.controller.session;

import java.util.List;

import com.dunkware.net.cluster.node.ClusterNode;
import com.dunkware.spring.cluster.DunkNetNode;
import com.dunkware.trade.service.stream.server.controller.StreamController;
import com.dunkware.trade.service.stream.server.controller.session.core.StreamSessionNodeCallback;
import com.dunkware.trade.tick.model.ticker.TradeTickerSpec;

public class StreamSessionNodeInput {
	
	
	private List<TradeTickerSpec> tickers;
	private DunkNetNode node;
	private List<StreamSessionExtension> extensionTypes;
	private StreamSession session;
	private StreamController stream; 
	private StreamSessionNodeCallback callBack;
	private int id; 
	public StreamSessionNodeInput(int id, List<TradeTickerSpec> tickers, DunkNetNode node, List<StreamSessionExtension> extensionTypes, 
			StreamSession session, StreamController stream, StreamSessionNodeCallback callBack) {
		this.tickers = tickers;
		this.id = id;
		this.node = node;
		this.extensionTypes = extensionTypes;
		this.session = session;
		this.stream = stream;
		this.callBack = callBack;
	}

	public List<TradeTickerSpec> getTickers() {
		return tickers;
	}

	public DunkNetNode getNode() { 
		return node;
	}
	
	public int getId() { 
		return id;
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
